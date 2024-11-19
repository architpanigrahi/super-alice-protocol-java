package com.superalice.packet.payload;

import lombok.Data;

import java.util.List;

@Data
public class DiscoveryResponsePayload {

    private Integer deviceId;
    private Integer noOfEntries;
    private List<DiscoveryResponsePeerEntryPayload> peerEntries;

}
