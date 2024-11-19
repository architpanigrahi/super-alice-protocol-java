package com.superalice.peer;

import com.superalice.devicemeta.DeviceIPTable;
import com.superalice.devicemeta.PositionTable;
import com.superalice.peer.bootstrap.PeerBootstrap;
import com.superalice.peer.edge.PeerEdge;
import com.superalice.peer.satellite.PeerSatellite;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PeerFactory {

    public static Peer createPeer(String peerTypeName, String hostAddress, int port, String bootstrapAddress) {
        PeerType peerType = PeerType.getPeerType(peerTypeName);
        if (peerType == null) {
            throw new IllegalArgumentException("Unknown peer type: " + peerTypeName);
        }

        switch (peerType) {
            case EDGE:
                return new PeerEdge();
            case BOOTSTRAP:
                return createPeerBootstrap(hostAddress, port);
            case SATELLITE:
                return new PeerSatellite();
            default:
                throw new IllegalArgumentException("Unknown peer type: " + peerTypeName);
        }
    }

    private static Peer createPeerBootstrap(String hostAddress, int port) {
        Peer bootstrapPeer = new PeerBootstrap();
        bootstrapPeer.setDeviceId(1);
        bootstrapPeer.setHostIP(hostAddress);
        bootstrapPeer.setPort(port);
        bootstrapPeer.setDeviceIPTable(new DeviceIPTable());
        bootstrapPeer.setPositionTable(new PositionTable());
        return bootstrapPeer;
    }

}
