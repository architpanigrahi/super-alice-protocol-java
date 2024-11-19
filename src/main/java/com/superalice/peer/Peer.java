package com.superalice.peer;

import com.superalice.devicemeta.DeviceIPTable;
import com.superalice.devicemeta.PositionTable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public abstract class Peer {

    public Integer deviceId;
    public DeviceIPTable deviceIPTable;
    public PositionTable positionTable;
    public String hostIP;
    public Integer port;


    public abstract String getPeerType();
    public abstract void startPeer();
//    public abstract void setDeviceAddress(String hostIP, int port);
//    public abstract void setBootstrapAddress(String bootstrapIP, int bootstrapPort);



}
