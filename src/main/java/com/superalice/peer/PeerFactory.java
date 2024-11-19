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
            case BOOTSTRAP:
                return createPeerBootstrap(hostAddress, port);
            case SATELLITE:
                return createPeerSatellite(hostAddress, port, bootstrapAddress);
            case EDGE:
                return createPeerEdge(hostAddress, port, bootstrapAddress);
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

    private static Peer createPeerSatellite(String hostAddress, int port, String bootstrapAddress) {
        Peer satellitePeer = new PeerSatellite();
        satellitePeer.setHostIP(hostAddress);
        satellitePeer.setPort(port);
        satellitePeer.setDeviceIPTable(new DeviceIPTable());
        satellitePeer.setPositionTable(new PositionTable());
        satellitePeer.setBootstrapAddress(bootstrapAddress);
        return satellitePeer;
    }

    private static Peer createPeerEdge(String hostAddress, int port, String bootstrapAddress) {
        Peer edgePeer = new PeerEdge();
        edgePeer.setHostIP(hostAddress);
        edgePeer.setPort(port);
        edgePeer.setDeviceIPTable(new DeviceIPTable());
        edgePeer.setPositionTable(new PositionTable());
        edgePeer.setBootstrapAddress(bootstrapAddress);
        return edgePeer;
    }

}
