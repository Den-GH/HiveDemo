package com.example.hivedemo.test;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.TimeUnit;

@Service
public class MqttPerformanceTest {
    public static void runAdvancedTest(String brokerUrl,
                                int totalClients,
                                int messagesPerClient,
                                int messageSize,
                                int qosLevel) throws Exception {
        ExecutorService executor = Executors.newFixedThreadPool(totalClients);
        CountDownLatch completionLatch = new CountDownLatch(totalClients);
        AtomicInteger activeClients = new AtomicInteger(0);
        AtomicLong totalLatency = new AtomicLong(0);
        AtomicLong successfulPublishes = new AtomicLong(0);

        long testStart = System.currentTimeMillis();

        // 分批次启动客户端，模拟真实场景
        int batchSize = Math.max(1, totalClients / 10);
        for (int batch = 0; batch < totalClients; batch += batchSize) {
            int currentBatchSize = Math.min(batchSize, totalClients - batch);
            System.out.println("启动批次 " + (batch / batchSize + 1) +
                    " (" + currentBatchSize + " 个客户端)");
            for (int i = 0; i < currentBatchSize; i++) {
                final int clientId = batch + i;
                executor.submit(() -> {
                    try {
                        MqttClient client = createAdvancedClient(brokerUrl, clientId);
                        activeClients.incrementAndGet();
                        // 执行消息发布测试
                        long clientStart = System.currentTimeMillis();
                        performMessageTest(
                                client,
                                clientId,
                                messagesPerClient,
                                messageSize,
                                qosLevel,
                                totalLatency,
                                successfulPublishes);
                        long clientEnd = System.currentTimeMillis();
                        System.out.println("客户端 " + clientId + " 完成, 耗时: " +
                                (clientEnd - clientStart) + "ms");
                        client.disconnect();
                        client.close();
                    } catch (Exception e) {
                        System.err.println("客户端 " + clientId + " 测试失败: " + e.getMessage());
                    } finally {
                        activeClients.decrementAndGet();
                        completionLatch.countDown();
                    }
                });
            }
            // 批次间延迟
//            Thread.sleep(1000);
        }

        completionLatch.await(300, TimeUnit.SECONDS);

        long testEnd = System.currentTimeMillis();
        printAdvancedResults(testStart, testEnd, totalClients, messagesPerClient, totalLatency, successfulPublishes);
        executor.shutdown();
    }

    private static MqttClient createAdvancedClient(String brokerUrl, int clientId)
            throws MqttException {
        String clientIdStr = "adv_stress_client_" + clientId;
        MqttClient client = new MqttClient(brokerUrl, clientIdStr, new MemoryPersistence());
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setConnectionTimeout(60);
        options.setKeepAliveInterval(120);
        options.setMaxInflight(100);
        options.setAutomaticReconnect(true);
        client.connect(options);
        return client;
    }

    private static void performMessageTest(MqttClient client, int clientId,
                                           int messageCount, int messageSize,
                                           int qosLevel, AtomicLong totalLatency,
                                           AtomicLong successfulPublishes) {
        String topic = "test/topic/" + clientId;
        String payload = generateAdvancedPayload(messageSize, clientId);
        for (int i = 0; i < messageCount; i++) {
            try {
                long publishStart = System.currentTimeMillis();
                MqttMessage message = new MqttMessage(payload.getBytes());
                message.setQos(qosLevel);
                message.setId(i);
                client.publish(topic, message);

//                token.waitForCompletion(5000);
                long publishEnd = System.currentTimeMillis();
                long latency = publishEnd - publishStart;
                totalLatency.addAndGet(latency);
                successfulPublishes.incrementAndGet();
                // 随机延迟，模拟真实流量模式
                Thread.sleep(ThreadLocalRandom.current().nextInt(10, 100));
            } catch (Exception e) {
                System.err.println("客户端 " + clientId + " 消息 " + i + " 发布失败");
            }
        }
    }

    private static String generateAdvancedPayload(int size, int clientId) {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"clientId\":").append(clientId)
                .append(",\"timestamp\":").append(System.currentTimeMillis())
                .append(",\"sequence\":").append(System.nanoTime())
                .append(",\"data\":\"");
        int currentSize = sb.length();
        for (int i = currentSize; i < size - 2; i++) {
            sb.append((char) ('a' + (i % 26)));
        }
        sb.append("\"}");
        return sb.toString();
    }

    private static void printAdvancedResults(long startTime,
                                             long endTime,
                                             int totalClients,
                                             int messagesPerClient,
                                             AtomicLong totalLatency,
                                             AtomicLong successfulPublishes) {
        long totalMessages = totalClients * (long) messagesPerClient;
        long duration = endTime - startTime;
        double throughput = (successfulPublishes.get() * 1000.0) / duration;
        double avgLatency = totalLatency.get() / (double) successfulPublishes.get();
        System.out.println("\n=== 高级测试结果 ===");
        System.out.println("总持续时间: " + duration + " ms");
        System.out.println("总消息数: " + totalMessages);
        System.out.println("成功发布: " + successfulPublishes.get());
        System.out.println("吞吐量: " + String.format("%.2f", throughput) + " msg/s");
        System.out.println("平均延迟: " + String.format("%.2f", avgLatency) + " ms");
        System.out.println("成功率: " + String.format("%.2f%%", (successfulPublishes.get() * 100.0 / totalMessages)));
    }

//    private static void printUsage() {
//        System.out.println("用法: java AdvancedStressTester <broker_url> [clients] [messages] [message_size] [qos]");
//        System.out.println("示例: java AdvancedStressTester tcp://localhost:1883 100 50 1024 1");
//    }

}
