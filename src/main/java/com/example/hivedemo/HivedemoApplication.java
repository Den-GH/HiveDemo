package com.example.hivedemo;


import com.hivemq.embedded.EmbeddedHiveMQ;
import com.hivemq.embedded.EmbeddedHiveMQBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.nio.file.Path;


@SpringBootApplication
public class HivedemoApplication {
    //    private static final String BROKER = "ssl://localhost:8883";
//    private static final String BROKER = "mqtt://localhost:1883";
    //    private static final String CLIENT_ID = "JavaMTLSClient";
//    private static final String TOPIC = "test/mtls";
    private static final String BROKER = "tcp://localhost:1883";
    private static final String CLIENT_ID = "mqtt-moquette-test11";
    //    private static final String TOPIC = "moquette-test-topic";
    private static final String TOPIC = "test";

    public static void main(String[] args) {
//        final EmbeddedHiveMQBuilder embeddedHiveMQBuilder = EmbeddedHiveMQ.builder();
////                .withConfigurationFolder(Path.of("/path/to/embedded-config-folder"))
////                .withDataFolder(Path.of("/path/to/embedded-data-folder"))
////                .withExtensionsFolder(Path.of("/path/to/embedded-extensions-folder"));
//
//        try (final EmbeddedHiveMQ hiveMQ = embeddedHiveMQBuilder.build()) {
//            hiveMQ.start().join();
//        } catch (final Exception ex) {
//            ex.printStackTrace();
//        }

//        try {
//            final HiveMQConfig config = HiveMQConfigBuilder.builder.build();
//            final EmbeddedHiveMQ hiveMQ = EmbeddedHiveMQ.builder().build();
//            hiveMQ.start().join();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }


//        try {
////            // 配置SSL上下文
////            SSLContext sslContext = SSLContext.getInstance("TLS");
////            // 加载客户端密钥库（包含客户端证书和私钥）
////            KeyStore keyStore = KeyStore.getInstance("PKCS12");
////            keyStore.load(new FileInputStream("certs/client.p12"), "123456".toCharArray());
////            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
////            kmf.init(keyStore, "123456".toCharArray());
////            // 加载信任库（CA证书）
////            KeyStore trustStore = KeyStore.getInstance("JKS");
////            trustStore.load(new FileInputStream("certs/truststore.jks"), "123456".toCharArray());
////            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
////            tmf.init(trustStore);
////            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
//
//
//            // 创建MqttClient
//            MqttClient client = new MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());
//            MqttConnectOptions options = new MqttConnectOptions();
////            options.setSocketFactory(sslContext.getSocketFactory());
//            options.setCleanSession(true);
//            options.setConnectionTimeout(30);
//            options.setKeepAliveInterval(60);
//            options.setAutomaticReconnect(true);
//            // 设置回调处理连接状态和消息
//            client.setCallback(new MqttCallback() {
//                @Override
//                public void connectionLost(Throwable cause) {
//                    System.out.println("1连接断开: " + cause.getMessage());
//                }
//
//                @Override
//                public void messageArrived(String topic, MqttMessage message) {
//                    System.out.println("2收到消息 - 主题: " + topic + ", 内容: " + new String(message.getPayload()));
//                }
//
//                @Override
//                public void deliveryComplete(IMqttDeliveryToken token) {
//                    System.out.println("3消息发送完成");
//                }
//            });
//            System.out.println("正在连接到: " + BROKER);
//            client.connect(options);
//            System.out.println("mTLS连接成功!");
//            // 订阅主题
//            client.subscribe(TOPIC);
//            System.out.println("已订阅主题: " + TOPIC);
////            // 发布测试消息
////            String messageContent = "Hello HiveMQ with mTLS222!";
////            MqttMessage message = new MqttMessage(messageContent.getBytes());
////            message.setQos(0);
////            client.publish(TOPIC, message);
////            System.out.println("消息已发布: " + messageContent);
////            // 保持连接一段时间
////            Thread.sleep(5000);
////            // 断开连接
////            client.disconnect();
////            client.close();
////            System.out.println("连接已关闭");
//        } catch (Exception e) {
//            System.err.println("mTLS连接失败: " + e.getMessage());
//            e.printStackTrace();
//        }


        SpringApplication.run(HivedemoApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner startup(final ApplicationContext ctx, final HiveMQEmbeddedStarter hiveMQ) {
//        return args -> {
//            // Do what ever we want ...
//            // ...
//
//            // Now start HiveMQ
//            hiveMQ.startup();
//
//            // Do other stuff ...
//            // ...
//        };
//    }
//
//    @Bean
//    public EmbeddedExtension embeddedExtension1() {
//        return EmbeddedExtension.builder()
//                .withId("my-embedded-extension 1")
//                .withName("My Embedded Extension #1")
//                .withVersion("1.0.0")
//                .withPriority(0)
//                .withStartPriority(1000)
//                .withAuthor("Me")
//                .withExtensionMain(new MyEmbeddedExtension(1))
//                .build();
//    }
//
//    @Bean
//    public EmbeddedExtension embeddedExtension2() {
//        return EmbeddedExtension.builder()
//                .withId("my-embedded-extension 2")
//                .withName("My Embedded Extension #2")
//                .withVersion("1.0.0")
//                .withPriority(10)
//                .withStartPriority(2000)
//                .withAuthor("Me")
//                .withExtensionMain(new MyEmbeddedExtension(2))
//                .build();
//    }


//    @Bean
//    public HiveMQ hiveMQ() {
//        HivemqGlobalConfiguration configuration = new HivemqGlobalConfiguration();
//        InMemoryConfigurationLoader loader = new InMemoryConfigurationLoader(configuration);
//        return HiveMQ.newHivemq().withConfigurationLoader(loader).start();
//    }

}
