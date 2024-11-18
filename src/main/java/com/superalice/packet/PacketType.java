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
    CONTROL((byte) 5),
    ERROR((byte) 6),
    KEEP_ALIVE((byte) 7),
    DISCOVERY((byte) 8),
    DISCOVERY_RESPONSE((byte) 9);

    private final byte code;

}
