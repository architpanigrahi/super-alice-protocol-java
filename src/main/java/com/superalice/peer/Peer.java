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
    public String bootstrapAddress;


    public abstract String getPeerType();

    public abstract void startPeer();


}
