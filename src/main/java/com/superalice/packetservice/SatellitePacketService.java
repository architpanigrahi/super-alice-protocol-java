package com.superalice.packetservice;

import com.superalice.devicemeta.ECIPosition;
import com.superalice.packet.Packet;
import com.superalice.packet.PacketType;
import com.superalice.peer.PeerType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SatellitePacketService {

    public static Packet createDiscoveryPacket() {
//        Packet discoveryPacket = new Packet();
//
//        discoveryPacket.setPacketType(PacketType.DISCOVERY);
//
//        // TODO: Mock ECI Coordinates
//        DiscoveryPayload discoveryPayload = new DiscoveryPayload();
////        discoveryPayload.setPeerType(PeerType.SATELLITE.getName());
//        discoveryPayload.getEciPosition().setX(0.0);
//        discoveryPayload.getEciPosition().setY(0.1);
//        discoveryPayload.getEciPosition().setZ(0.2);
//
//        discoveryPacket.setPayload(discoveryPayload.toString().getBytes());
//
//        return discoveryPacket;
        return null;

    }

}
