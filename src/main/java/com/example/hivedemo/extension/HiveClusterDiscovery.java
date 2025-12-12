package com.example.hivedemo.extension;

import com.hivemq.extension.sdk.api.annotations.NotNull;
import com.hivemq.extension.sdk.api.services.cluster.ClusterDiscoveryCallback;
import com.hivemq.extension.sdk.api.services.cluster.parameter.ClusterDiscoveryInput;
import com.hivemq.extension.sdk.api.services.cluster.parameter.ClusterDiscoveryOutput;

public class HiveClusterDiscovery  implements ClusterDiscoveryCallback {
    // this is a placeholder for an implementation of your custom external service
//    MyExternalService externalService = new MyExternalService();

    @Override
    public void init(@NotNull ClusterDiscoveryInput clusterDiscoveryInput, @NotNull ClusterDiscoveryOutput clusterDiscoveryOutput) {

    }

    @Override
    public void reload(@NotNull ClusterDiscoveryInput clusterDiscoveryInput, @NotNull ClusterDiscoveryOutput clusterDiscoveryOutput) {

    }

    @Override
    public void destroy(@NotNull ClusterDiscoveryInput clusterDiscoveryInput) {

    }
}
