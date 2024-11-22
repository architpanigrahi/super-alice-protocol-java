package com.superalice.peer.bootstrap;

import com.superalice.devicemeta.DeviceIPTypeEntry;
import com.superalice.peer.PeerType;
import lombok.extern.slf4j.Slf4j;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

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
            log.info("Peer listener started on port {}", peer.port);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                byte[] packetDataByteArray = packet.getData();
                BootstrapFunction.handleRequest(packetDataByteArray, peer);
            }

        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
    }
}
