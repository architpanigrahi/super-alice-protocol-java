package com.superalice.peer;

public enum PeerType {

    BOOTSTRAP("BOOTSTRAP_NODE"),
    SATELLITE("SATELLITE"),
    EDGE("EDGE_DEVICE");

    private final String name;

    PeerType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static PeerType getPeerType(String peerType) {
        for (PeerType type : PeerType.values()) {
            if (type.name.equalsIgnoreCase(peerType)) {
                return type;
            }
        }
        return null;
    }


}
