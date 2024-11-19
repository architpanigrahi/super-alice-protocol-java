package com.superalice.peer.edge;

import com.superalice.peer.Peer;
import com.superalice.peer.PeerType;

public class PeerEdge extends Peer {

    @Override
    public String getPeerType() {
        return PeerType.EDGE.getName();
    }

    @Override
    public void startPeer() {

    }

}
