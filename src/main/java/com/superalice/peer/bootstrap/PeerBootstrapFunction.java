package com.superalice.peer.bootstrap;

import com.superalice.devicemeta.DeviceIPTypeEntry;
import com.superalice.devicemeta.ECIPosition;
import com.superalice.devicemeta.PositionEntry;
import com.superalice.packet.Packet;
import com.superalice.packet.PacketType;
import com.superalice.packet.payload.DiscoveryResponsePayload;
import com.superalice.packet.payload.DiscoveryResponsePeerEntryPayload;
import com.superalice.packet.payload.HandshakePayload;
import com.superalice.packet.payload.KeepAlivePayload;
import com.superalice.packet.serdes.HandshakePayloadSerDes;
import com.superalice.packet.serdes.KeepAlivePayloadSerDes;
import com.superalice.packet.serdes.PacketSerDes;
import com.superalice.positionservice.ECIPositionService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Map;

import static com.superalice.peer.serdes.IPHostAddressSerDes.deserialize;
import static com.superalice.peer.serdes.IPHostAddressSerDes.serialize;

@Slf4j
public class PeerBootstrapFunction {

    public static void handleRequest(byte[] packetDataByteArray, PeerBootstrap peer) {
        Packet packet = PacketSerDes.deserialize(packetDataByteArray);
        PacketType packetType = packet.getPacketType();
        switch (packetType) {
            case HANDSHAKE:
                handleHandshakeRequest(packet, peer);
                break;
            case KEEP_ALIVE:
                handleKeepAliveRequest(packet, peer);
                break;
            case DISCOVERY:
                handleDiscoveryRequest(packet, peer);
                break;
        }
    }

    private static void handleHandshakeRequest(Packet packet, PeerBootstrap peer) {
        HandshakePayload handshakePayload = new HandshakePayloadSerDes().deserialize(packet.getPayload());
        log.info("Handshake request received: {}", handshakePayload);

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

    private static void handleKeepAliveRequest(Packet packet, PeerBootstrap peer) {
        KeepAlivePayload keepAlivePayload = new KeepAlivePayloadSerDes().deserialize(packet.getPayload());
        log.info("Keep alive request received: {}", keepAlivePayload);

        PositionEntry positionEntry = new PositionEntry();
        positionEntry.setTimestamp(System.currentTimeMillis());
        ECIPosition eciPosition = new ECIPosition();
        eciPosition.setX(keepAlivePayload.getEciPosition().getX());
        eciPosition.setY(keepAlivePayload.getEciPosition().getY());
        eciPosition.setZ(keepAlivePayload.getEciPosition().getZ());
        positionEntry.setEciPosition(eciPosition);
        peer.positionTable.getTable().put(packet.getSourceId(), positionEntry);
    }

    private static void handleDiscoveryRequest(Packet packet, PeerBootstrap peer) {
        log.info("Discovery request received: {}", packet);

        Integer sourceId = packet.getSourceId();
        // Prepare for Discovery Response Packet
        ECIPosition currentPosition = peer.positionTable.getTable().get(sourceId).getEciPosition();
        DiscoveryResponsePayload discoveryResponsePayload = new DiscoveryResponsePayload();
        discoveryResponsePayload.setPeerEntries(new ArrayList<>());
        for (Map.Entry<Integer, PositionEntry> entry : peer.positionTable.getTable().entrySet()) {
            PositionEntry positionEntry = entry.getValue();
            Double distance = ECIPositionService.calculateDistance(currentPosition, positionEntry.getEciPosition());
            if (distance < 1000000.0) {
                DiscoveryResponsePeerEntryPayload discoveryResponsePeerEntryPayload = new DiscoveryResponsePeerEntryPayload();
                discoveryResponsePeerEntryPayload.setDeviceId(entry.getKey());
                discoveryResponsePeerEntryPayload.setPeerIPAddress(serialize(peer.deviceIPTable.getTable().get(entry.getKey()).getIpAddress()));
                discoveryResponsePeerEntryPayload.setPeerTypeId(peer.deviceIPTable.getTable().get(entry.getKey()).getPeerType());
                discoveryResponsePayload.getPeerEntries().add(discoveryResponsePeerEntryPayload);
            }

        }

        // Create DISCOVERY response packet

    }



}
