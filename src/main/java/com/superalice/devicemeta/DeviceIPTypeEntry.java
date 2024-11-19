package com.superalice.devicemeta;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeviceIPTypeEntry implements Serializable {

    private String ipAddress;
    private String peerType;

}
