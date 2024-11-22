package com.superalice.peer.satellite;

import com.superalice.peer.Peer;
import com.superalice.peer.PeerType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PeerSatellite extends Peer {

    @Override
    public String getPeerType() {
        return PeerType.SATELLITE.getName();
    }

    @Override
    public void startPeer() {

        // Send handshake request
        PeerSatelliteFunction.sendHandshakeRequest(this);

        // Listener thread
        Thread listenerThread = new Thread(new PeerSatelliteListenerThread(this));
        listenerThread.start();

        // Keep alive thread
        Thread keepAliveThread = new Thread(() -> {
            while (true) {
                PeerSatelliteFunction.sendKeepAliveRequest(this);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.error("Thread interrupted", e);
                }
            }
        });
        keepAliveThread.start();

        // Discovery thread
        Thread discoveryThread = new Thread(() -> {
            while (true) {
                PeerSatelliteFunction.sendDiscoveryRequest(this);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    log.error("Thread interrupted", e);
                }
            }
        });
        discoveryThread.start();

    }

}
