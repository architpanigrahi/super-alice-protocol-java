package com.superalice.peer.edge;

import com.superalice.devicemeta.ECIPosition;
import com.superalice.packet.Packet;
import com.superalice.packet.PacketType;
import com.superalice.packet.payload.HandshakePayload;
import com.superalice.packet.serdes.HandshakePayloadSerDes;
import com.superalice.packet.serdes.PacketSerDes;
import com.superalice.peer.PeerType;
import lombok.extern.slf4j.Slf4j;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

import static com.superalice.common.PeerConstant.BOOTSTRAP_DEVICE_ID;
import static com.superalice.peer.serdes.IPHostAddressSerDes.deserialize;
import static com.superalice.peer.serdes.IPHostAddressSerDes.serialize;

@Slf4j
public class PeerEdgeFunction {

    public static void handleRequest(byte[] packetDataByteArray, PeerEdge peerEdge) {
        Packet packet = PacketSerDes.deserialize(packetDataByteArray);
        PacketType packetType = packet.getPacketType();

    }

    public static void sendHandshakeRequest(PeerEdge peerEdge) {
        Packet packet = new Packet();
        packet.setSourceId(peerEdge.getDeviceId());
        packet.setDestinationId(BOOTSTRAP_DEVICE_ID);
        packet.setPacketType(PacketType.HANDSHAKE);
        packet.setPriority((byte) 1);
        packet.setSequenceNumber(new Random().nextInt());
        packet.setTimestamp(System.currentTimeMillis());
        packet.setFragmentIndex((short) 0);
        packet.setTotalFragments((short) 1);
        packet.setCrc((short) 0);
        packet.setReserved((short) 0);
        packet.setPayloadType((short) 0);

        HandshakePayload handshakePayload = new HandshakePayload();
        handshakePayload.setHostIPAddress(serialize(peerEdge.getHostIP() + ":" + peerEdge.getPort()));
        handshakePayload.setPeerTypeId(PeerType.EDGE.getId());
        ECIPosition eciPosition = new ECIPosition();
        eciPosition.setX(0.0);
        eciPosition.setY(0.0);
        eciPosition.setZ(0.0);
        handshakePayload.setEciPosition(eciPosition); // TODO: Introduce mock current location

        byte[] payload = new HandshakePayloadSerDes().serialize(handshakePayload);
        packet.setPayload(payload);

        byte[] packetBytes = PacketSerDes.serialize(packet);

        // Send UDP Packet
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress bootstrapAddress = InetAddress.getByName(peerEdge.getBootstrapAddress().split(":")[0]);
            int bootstrapPort = Integer.parseInt(peerEdge.getBootstrapAddress().split(":")[1]);
            DatagramPacket datagramPacket = new DatagramPacket(packetBytes, packetBytes.length, bootstrapAddress, bootstrapPort);
            log.info("Sending HANDSHAKE Packet to Bootstrap Server");
            socket.send(datagramPacket);
        } catch (Exception e) {
            log.error("Error occurred while sending HANDSHAKE Packet to Bootstrap Server {}", e.getMessage());
        }

    }

}
