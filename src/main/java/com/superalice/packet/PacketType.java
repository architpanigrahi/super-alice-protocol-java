package com.superalice.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PacketType {

    HANDSHAKE((byte) 0),
    DATA((byte) 1),
    ACK((byte) 2),
    NACK((byte) 3),
    CONTROL((byte) 4),
    ERROR((byte) 5),
    KEEP_ALIVE((byte) 6),
    DISCOVERY((byte) 7),
    DISCOVERY_RESPONSE((byte) 8);

    private final byte code;

}
