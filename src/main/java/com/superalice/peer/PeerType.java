package com.superalice.peer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PeerType {

    BOOTSTRAP("BOOTSTRAP_NODE", (byte) 1),
    SATELLITE("SATELLITE", (byte) 2),
    EDGE("EDGE_DEVICE", (byte) 3);

    private final String name;
    private final byte id;

    public static PeerType getPeerType(String peerType) {
        for (PeerType type : PeerType.values()) {
            if (type.name.equalsIgnoreCase(peerType)) {
                return type;
            }
        }
        return null;
    }

    public static PeerType getPeerType(int id) {
        for (PeerType type : PeerType.values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }


}
