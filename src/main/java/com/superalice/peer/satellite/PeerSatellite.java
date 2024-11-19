package com.superalice.peer.satellite;

import com.superalice.peer.Peer;
import com.superalice.peer.PeerType;

public class PeerSatellite extends Peer {

    @Override
    public String getPeerType() {
        return PeerType.SATELLITE.getName();
    }

    @Override
    public void startPeer() {

        Thread thread = new Thread(new PeerSatelliteListenerThread(this));
        thread.start();
    }

}
