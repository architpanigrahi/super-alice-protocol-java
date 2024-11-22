package com.superalice.peer.satellite;

import lombok.extern.slf4j.Slf4j;

import java.net.DatagramPacket;
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
            log.info("Satellite listener started on port {}", peer.port);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                byte[] packetDataByteArray = packet.getData();
                PeerSatelliteFunction.handleRequest(packetDataByteArray, peer);
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
    }
}
