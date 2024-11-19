package com.superalice.peer.bootstrap;

import com.superalice.devicemeta.DeviceIPTable;
import com.superalice.devicemeta.DeviceIPTypeEntry;
import com.superalice.devicemeta.PositionTable;
import com.superalice.peer.PeerType;
import lombok.extern.slf4j.Slf4j;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Random;

@Slf4j
public class PeerBootstrapListenerThread implements Runnable {

    private final int port;
    private final DeviceIPTable deviceIPTable;
    private final PositionTable positionTable;

    public PeerBootstrapListenerThread(int port, DeviceIPTable deviceIPTable, PositionTable positionTable) {
        this.port = port;
        this.deviceIPTable = deviceIPTable;
        this.positionTable = positionTable;
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];

        try (DatagramSocket socket = new DatagramSocket(port)) {
            log.info("Peer listener started on port {}", port);

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                DeviceIPTypeEntry deviceIPTypeEntry = new DeviceIPTypeEntry();
                deviceIPTypeEntry.setIpAddress(packet.getAddress() + ":" + packet.getPort());
                deviceIPTypeEntry.setPeerType(PeerType.SATELLITE.getName());

                deviceIPTable.getTable().put(new Random().nextInt(10), deviceIPTypeEntry);

                String received = new String(packet.getData(), 0, packet.getLength());
                log.info("Received: {} from {}:{}", received, packet.getAddress(), packet.getPort());
            }

        } catch (Exception e) {
            log.error("{}", e.getMessage());
        }
    }
}
