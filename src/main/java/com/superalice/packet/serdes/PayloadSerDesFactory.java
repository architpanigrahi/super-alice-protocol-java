package com.superalice.packet.serdes;

import com.superalice.packet.PacketType;

public class PayloadSerDesFactory {

    public static PayloadSerDes getPayloadSerDes(PacketType packetType) {
        switch (packetType) {
            case HANDSHAKE:
                return new HandshakePayloadSerDes();
            case DATA:
                return null;
            case DISCOVERY:
                return new DiscoveryResponsePayloadSerDes();
            case KEEP_ALIVE:
                return new KeepAlivePayloadSerDes();
            case ROUTE:
                return new RoutePayloadSerDes();
            case RETRANSMIT:
                return new RetransmitPayloadSerDes();
            case PULL:
                return new PullPayloadSerDes();

            default:
                throw new IllegalArgumentException("Unknown packet type: " + packetType);
        }
    }

}
