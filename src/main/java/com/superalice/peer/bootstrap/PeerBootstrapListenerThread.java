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

                DeviceIPTypeEntry deviceIPTypeEntry = new DeviceIPTypeEntry();
                deviceIPTypeEntry.setIpAddress(packet.getAddress() + ":" + packet.getPort());
                deviceIPTypeEntry.setPeerType(PeerType.SATELLITE.getName());

                peer.deviceIPTable.getTable().put(new Random().nextInt(10), deviceIPTypeEntry);

                String received = new String(packet.getData(), 0, packet.getLength());
                log.info("Received: {} from {}:{}", received, packet.getAddress(), packet.getPort());
            }

        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
    }
}
