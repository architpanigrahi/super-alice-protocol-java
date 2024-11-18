package com.superalice.peer;

public class PeerBootstrap extends Peer {

    @Override
    public String getPeerType() {
        return PeerType.BOOTSTRAP.getName();
    }

    @Override
    public void setDeviceAddress(String hostIP, int port) {

    }

    @Override
    public void setBootstrapAddress(String bootstrapIP, int bootstrapPort) {

    }
}
