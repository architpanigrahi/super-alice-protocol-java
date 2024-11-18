package com.superalice.peer;

public class PeerSatellite extends Peer {

    @Override
    public String getPeerType() {
        return PeerType.SATELLITE.getName();
    }

    @Override
    public void setDeviceAddress(String hostIP, int port) {

    }

    @Override
    public void setBootstrapAddress(String bootstrapIP, int bootstrapPort) {

    }
}
