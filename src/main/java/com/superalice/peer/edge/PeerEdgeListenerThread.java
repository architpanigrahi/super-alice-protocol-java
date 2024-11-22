package com.superalice.peer.edge;

import lombok.extern.slf4j.Slf4j;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

@Slf4j
public class PeerEdgeListenerThread implements Runnable {

    private final PeerEdge peer;

    public PeerEdgeListenerThread(PeerEdge peer) {
        this.peer = peer;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(peer.port)) {
            log.info("Edge listener started on port {}", peer.port);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                byte[] packetDataByteArray = packet.getData();
                PeerEdgeFunction.handleRequest(packetDataByteArray, peer);
            }
        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
    }
}
