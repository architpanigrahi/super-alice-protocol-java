package com.superalice.peer.bootstrap;

import com.superalice.peer.Peer;
import com.superalice.peer.PeerType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PeerBootstrap extends Peer {

    @Override
    public String getPeerType() {
        return PeerType.BOOTSTRAP.getName();
    }

    @Override
    public void startPeer() {
        Thread thread = new Thread(new PeerBootstrapListenerThread(this));
        thread.start();

        // Test
        Thread thread1 = new Thread(() -> {
            while (true) {
                log.info("Current Device IP table {}", this.deviceIPTable);
                try {
                    Thread.sleep(5000); // Sleep for 5 seconds
                } catch (InterruptedException e) {
                    log.error("Thread interrupted", e);
                }
            }
        });
        thread1.start();
    }

}
