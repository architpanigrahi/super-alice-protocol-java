package com.superalice.packet.payload;

import lombok.Data;

import java.util.List;

@Data
public class DiscoveryResponsePayload {

    private List<DiscoveryResponsePeerEntryPayload> peerEntries;

}
