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

        // TODO : Remove this later
        Thread thread1 = new Thread(() -> {
            while (true) {
                log.info("Current Device IP table {}", this.deviceIPTable);
                log.info("Current Position Table {}", this.positionTable);
                try {
                    Thread.sleep(10000); // Sleep for 5 seconds
                } catch (InterruptedException e) {
                    log.error("Thread interrupted", e);
                }
            }
        });
        thread1.start();
    }

}
