package com.superalice.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PacketType {

    HANDSHAKE((byte) 0),
    DATA((byte) 1),
    DISCOVERY((byte) 2),
    KEEP_ALIVE((byte) 3),
    ROUTE((byte) 4),
    RETRANSMIT((byte) 5),
    PULL((byte) 6);

    private final byte code;

}
