package com.example.hivedemo.extension;

import com.hivemq.extension.sdk.api.client.ClientContext;
import com.hivemq.extension.sdk.api.client.parameter.InitializerInput;
import com.hivemq.extension.sdk.api.interceptor.publish.PublishInboundInterceptor;
import com.hivemq.extension.sdk.api.interceptor.publish.parameter.PublishInboundInput;
import com.hivemq.extension.sdk.api.interceptor.publish.parameter.PublishInboundOutput;
import com.hivemq.extension.sdk.api.packets.publish.PublishPacket;
import com.hivemq.extension.sdk.api.services.intializer.ClientInitializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class HiveMQClientInterceptor implements ClientInitializer {
    @Override
    public void initialize(InitializerInput initializerInput, ClientContext clientContext) {
        clientContext.addPublishInboundInterceptor(new PublishInboundInterceptor() {
            @Override
            public void onInboundPublish(PublishInboundInput publishInboundInput,
                                         PublishInboundOutput publishInboundOutput) {
                //set the topic to "new/topic"
//                publishInboundOutput.getPublishPacket().setTopic("new/topic");
//                publishInboundOutput.getPublishPacket().setTopic("new/topic");
                PublishPacket publishPacket = publishInboundInput.getPublishPacket();
                String topic = publishPacket.getTopic();
                String payload = StandardCharsets.UTF_8.decode(publishPacket.getPayload().orElse(ByteBuffer.wrap(new byte[0]))).toString();

                System.out.println("Received message on topic: " + topic + " with payload: " + payload);

//                // 示例：拦截特定主题的消息并修改
//                if (topic.startsWith("secure/")) {
//                    // 可以在这里实现加密、脱敏等处理
//                    System.out.println("Processing secure message...");
//                }
            }
        });
    }
}
