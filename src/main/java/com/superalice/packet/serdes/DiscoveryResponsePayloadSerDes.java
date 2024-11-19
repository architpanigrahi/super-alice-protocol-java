package com.superalice.packet.serdes;

import com.superalice.devicemeta.ECIPosition;
import com.superalice.packet.payload.DiscoveryResponsePayload;
import com.superalice.packet.payload.DiscoveryResponsePeerEntryPayload;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class DiscoveryResponsePayloadSerDes implements PayloadSerDes<DiscoveryResponsePayload> {

    /**
     * Serialize DiscoveryResponsePayload
     * Total of  = 11 bytes * n
     * @param payload
     * @return
     */
    @Override
    public byte[] serialize(DiscoveryResponsePayload payload) {
        int noOfEntries = payload.getPeerEntries().size();

        // Allocate buffer space
        ByteBuffer buffer = ByteBuffer.allocate(11 * noOfEntries);
        for (int i = 0; i < noOfEntries; i++) {
            buffer.putInt(payload.getPeerEntries().get(i).getDeviceId());
            buffer.put(payload.getPeerEntries().get(i).getPeerTypeId());
            buffer.putInt(payload.getPeerEntries().get(i).getPeerIP());
            buffer.putShort(payload.getPeerEntries().get(i).getPeerPort());
        }
        return buffer.array();
    }

    /**
     * Deserialize DiscoveryResponsePayload
     * @param bytes
     * @return
     */
    @Override
    public DiscoveryResponsePayload deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        DiscoveryResponsePayload payload = new DiscoveryResponsePayload();

        // Initialize
        payload.setPeerEntries(new ArrayList<>());

        while (buffer.hasRemaining()) {
            DiscoveryResponsePeerEntryPayload peerEntry = new DiscoveryResponsePeerEntryPayload();
            peerEntry.setDeviceId(buffer.getInt());
            peerEntry.setPeerTypeId(buffer.get());
            peerEntry.setPeerIP(buffer.getInt());
            peerEntry.setPeerPort(buffer.getShort());

            payload.getPeerEntries().add(peerEntry);
        }

        return payload;
    }
}
