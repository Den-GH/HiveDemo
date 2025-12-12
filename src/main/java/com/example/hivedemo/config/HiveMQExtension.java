package com.example.hivedemo.config;


import com.example.hivedemo.extension.HiveClusterDiscovery;
import com.example.hivedemo.extension.HiveMQClientInterceptor;
import com.hivemq.extension.sdk.api.ExtensionMain;
import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.parameter.ExtensionStartInput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStartOutput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStopInput;
import com.hivemq.extension.sdk.api.parameter.ExtensionStopOutput;
import com.hivemq.extension.sdk.api.services.Services;

public class HiveMQExtension implements ExtensionMain {

    @Override
    public void extensionStart(@NotNull ExtensionStartInput extensionStartInput,
                               @NotNull ExtensionStartOutput extensionStartOutput) {
        System.out.println("====HiveMQ Extension started");
        // 注册发布消息拦截器
        Services.initializerRegistry().setClientInitializer(new HiveMQClientInterceptor());
//        // 注册Cluster Discovery
//        Services.clusterService().addDiscoveryCallback(new HiveClusterDiscovery());

    }

    @Override
    public void extensionStop(@NotNull ExtensionStopInput extensionStopInput,
                              @NotNull ExtensionStopOutput extensionStopOutput) {

    }
}
