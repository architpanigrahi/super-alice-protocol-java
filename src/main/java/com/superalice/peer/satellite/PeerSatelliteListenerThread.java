package com.superalice.peer.satellite;

import com.superalice.devicemeta.DeviceIPTable;
import com.superalice.devicemeta.PositionTable;
import lombok.extern.slf4j.Slf4j;

import java.net.DatagramSocket;

@Slf4j
public class PeerSatelliteListenerThread implements Runnable {

    private final PeerSatellite peer;

    public PeerSatelliteListenerThread(PeerSatellite peer) {
        this.peer = peer;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(peer.port)) {

        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
    }
}
