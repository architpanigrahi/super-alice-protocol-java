package com.superalice.peer;

import lombok.Getter;

@Getter
public abstract class Peer {

    public Long deviceId;
    public abstract String getPeerType();


    public abstract void setDeviceAddress(String hostIP, int port);
    public abstract void setBootstrapAddress(String bootstrapIP, int bootstrapPort);



}
