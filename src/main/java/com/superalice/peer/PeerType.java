package com.superalice.peer;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PeerType {

    BOOTSTRAP("BOOTSTRAP_NODE", 1),
    SATELLITE("SATELLITE", 2),
    EDGE("EDGE_DEVICE", 3);

    private final String name;
    private final int id;

    public static PeerType getPeerType(String peerType) {
        for (PeerType type : PeerType.values()) {
            if (type.name.equalsIgnoreCase(peerType)) {
                return type;
            }
        }
        return null;
    }

//    public static String getPeerType(int id) {
//
//    }


}
