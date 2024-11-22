package com.superalice.peer.satellite;

import com.superalice.peer.Peer;
import com.superalice.peer.PeerType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PeerSatellite extends Peer {

    @Override
    public String getPeerType() {
        return PeerType.SATELLITE.getName();
    }

    @Override
    public void startPeer() {

        PeerSatelliteFunction.sendHandshakeRequest(this);

//        Thread thread = new Thread(new PeerSatelliteListenerThread(this));
//        thread.start();
    }

//    private void sendDiscoveryPacket() {
//        try (DatagramSocket socket = new DatagramSocket()) {
//            byte[] packetBytes = SatellitePacketService.createDiscoveryPacket().toString().getBytes();
//
//            InetAddress bootstrapAddress = InetAddress.getByName(this.getBootstrapAddress().split(":")[0]);
//            DatagramPacket discoveryPacket = new DatagramPacket(packetBytes, packetBytes.length, bootstrapAddress,
//                    Integer.parseInt(this.getBootstrapAddress().split(":")[1]));
//
//            log.info("Sending Discovery Packet {}", discoveryPacket);
//            socket.send(discoveryPacket);
//
//        } catch (Exception e) {
//
//        }
//    }

}
