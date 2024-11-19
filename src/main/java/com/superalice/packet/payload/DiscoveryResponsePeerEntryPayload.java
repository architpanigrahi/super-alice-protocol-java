package com.superalice.packet.payload;

import com.superalice.devicemeta.ECIPosition;
import lombok.Data;

@Data
public class DiscoveryResponsePeerEntryPayload {

    private Integer deviceId;
    private Byte peerTypeId;
    private Integer peerIP;
    private Short peerPort;
    private ECIPosition eciPosition;
    private Long timestamp;


}
