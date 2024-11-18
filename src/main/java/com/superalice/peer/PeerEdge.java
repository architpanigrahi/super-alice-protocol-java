package com.superalice.peer;

public class PeerEdge extends Peer{

    @Override
    public String getPeerType() {
        return PeerType.EDGE.getName();
    }

    @Override
    public void setDeviceAddress(String hostIP, int port) {

    }

    @Override
    public void setBootstrapAddress(String bootstrapIP, int bootstrapPort) {

    }
}
