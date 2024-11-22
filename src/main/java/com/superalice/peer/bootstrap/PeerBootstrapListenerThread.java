package com.superalice.peer.bootstrap;

import lombok.extern.slf4j.Slf4j;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

@Slf4j
public class PeerBootstrapListenerThread implements Runnable {

    private final PeerBootstrap peer;

    public PeerBootstrapListenerThread(PeerBootstrap peer) {
        this.peer = peer;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(peer.port)) {
            log.info("Bootstrap listener started on port {}", peer.port);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                byte[] packetDataByteArray = packet.getData();
                PeerBootstrapFunction.handleRequest(packetDataByteArray, peer);
            }

        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
    }
}
