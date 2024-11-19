package com.superalice.packet.payload;

import com.superalice.devicemeta.ECIPosition;
import lombok.Data;

import java.io.Serializable;

@Data
public class DiscoveryPayload implements Serializable {

    private Byte peerTypeId;
    private ECIPosition eciPosition;


}
