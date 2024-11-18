package com.superalice.peer;

public class PeerFactory {

    public static Peer createPeer(String peerTypeName, int port, String bootstrapAddress) {
        PeerType peerType = PeerType.getPeerType(peerTypeName);
        if (peerType == null) {
            throw new IllegalArgumentException("Unknown peer type: " + peerTypeName);
        }

        switch (peerType) {
            case EDGE:
                return new PeerEdge();
            case BOOTSTRAP:
                return new PeerBootstrap();
            case SATELLITE:
                return new PeerSatellite();
            default:
                throw new IllegalArgumentException("Unknown peer type: " + peerTypeName);
        }
    }

}
