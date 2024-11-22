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

        // Send Handshake request
        PeerEdgeFunction.sendHandshakeRequest(this);

        // Listener thread
        Thread listenerThread = new Thread(new PeerEdgeListenerThread(this));
        listenerThread.start();

    }

}
