package com.superalice.peer.bootstrap;

import com.superalice.devicemeta.DeviceIPTypeEntry;
import com.superalice.devicemeta.ECIPosition;
import com.superalice.devicemeta.PositionEntry;
import com.superalice.packet.Packet;
import com.superalice.packet.PacketType;
import com.superalice.packet.payload.DiscoveryResponsePeerEntryPayload;
import com.superalice.packet.payload.HandshakePayload;
import com.superalice.packet.serdes.HandshakePayloadSerDes;
import com.superalice.packet.serdes.PacketSerDes;
import com.superalice.positionservice.ECIPositionService;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class BootstrapFunction {

    public static void handleRequest(byte[] packetDataByteArray, PeerBootstrap peer) {
        Packet packet = PacketSerDes.deserialize(packetDataByteArray);
        PacketType packetType = packet.getPacketType();
        switch (packetType) {
            case HANDSHAKE:
                handleHandshakeRequest(packet, peer);
            case DISCOVERY:
                handleDiscoveryRequest(packet, peer);
        }
    }

    private static void handleHandshakeRequest(Packet packet, PeerBootstrap peer) {
        HandshakePayload handshakePayload = new HandshakePayloadSerDes().deserialize(packet.getPayload());
        log.info("Handshake : {}", handshakePayload);

        DeviceIPTypeEntry deviceIPTypeEntry = new DeviceIPTypeEntry();
        deviceIPTypeEntry.setPeerType(handshakePayload.getPeerTypeId());
        deviceIPTypeEntry.setIpAddress(deserialize(handshakePayload.getHostIPAddress()));
        peer.deviceIPTable.getTable().put(packet.getSourceId(), deviceIPTypeEntry);

        PositionEntry positionEntry = new PositionEntry();
        positionEntry.setTimestamp(System.currentTimeMillis());
        ECIPosition eciPosition = new ECIPosition();
        eciPosition.setX(handshakePayload.getEciPosition().getX());
        eciPosition.setY(handshakePayload.getEciPosition().getY());
        eciPosition.setZ(handshakePayload.getEciPosition().getZ());
        positionEntry.setEciPosition(eciPosition);
        peer.positionTable.getTable().put(packet.getSourceId(), positionEntry);
    }

    private static void handleDiscoveryRequest(Packet packet, PeerBootstrap peer) {
        Integer sourceId = packet.getSourceId();

        // Prepare for Discovery Response Packet
        ECIPosition currentPosition = peer.positionTable.getTable().get(sourceId).getEciPosition();
        for (Map.Entry<Integer, PositionEntry> entry : peer.positionTable.getTable().entrySet()) {
            PositionEntry positionEntry = entry.getValue();
            Double distance = ECIPositionService.calculateDistance(currentPosition, positionEntry.getEciPosition());
            if (distance < 1000000.0) {
                DiscoveryResponsePeerEntryPayload discoveryResponsePeerEntryPayload = new DiscoveryResponsePeerEntryPayload();
                discoveryResponsePeerEntryPayload.setDeviceId(entry.getKey());
                discoveryResponsePeerEntryPayload.setPeerIPAddress(serialize(peer.deviceIPTable.getTable().get(entry.getKey()).getIpAddress()));
            }

        }
    }

    public static byte[] serialize(String address) {
        // Split into IP and port
        String[] parts = address.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid address format. Must be 'IP:Port'.");
        }

        String ip = parts[0]; // "255.255.255.255"
        int port = Integer.parseInt(parts[1]); // 8080

        // Split the IP into octets
        String[] octets = ip.split("\\.");
        if (octets.length != 4) {
            throw new IllegalArgumentException("Invalid IP address format.");
        }

        // Create the byte array
        byte[] result = new byte[6];

        // Convert IP octets to bytes
        for (int i = 0; i < 4; i++) {
            int octet = Integer.parseInt(octets[i]);
            if (octet < 0 || octet > 255) {
                throw new IllegalArgumentException("IP octet out of range: " + octet);
            }
            result[i] = (byte) octet; // Cast to byte (will store as signed)
        }

        // Convert port to two bytes (big-endian)
        result[4] = (byte) (port >> 8);  // High byte
        result[5] = (byte) (port & 0xFF); // Low byte

        return result;
    }

    public static String deserialize(byte[] data) {
        if (data.length != 6) {
            throw new IllegalArgumentException("Invalid data length. Must be 6 bytes.");
        }

        // Extract IP address
        StringBuilder ip = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int octet = Byte.toUnsignedInt(data[i]); // Convert signed byte to unsigned int
            ip.append(octet);
            if (i < 3) {
                ip.append(".");
            }
        }

        // Extract port
        int port = ((data[4] & 0xFF) << 8) | (data[5] & 0xFF);

        return ip + ":" + port;
    }


}
