package com.superalice.packet.serdes;

import com.superalice.devicemeta.ECIPosition;
import com.superalice.packet.payload.DiscoveryResponsePayload;
import com.superalice.packet.payload.DiscoveryResponsePeerEntryPayload;

import java.nio.ByteBuffer;
import java.util.ArrayList;

public class DiscoveryResponsePayloadSerDes implements PayloadSerDes<DiscoveryResponsePayload> {

    /**
     * Serialize DiscoveryResponsePayload
     * Total of  = 4 (Newly assigned device IP) + 4 (n) + 43*n = 8 bytes + 43*n
     * @param payload
     * @return
     */
    @Override
    public byte[] serialize(DiscoveryResponsePayload payload) {
        int deviceId = payload.getDeviceId();
        int noOfEntries = payload.getNoOfEntries();

        // Allocate buffer space
        ByteBuffer buffer = ByteBuffer.allocate(8 + noOfEntries*43);
        buffer.putInt(deviceId);
        buffer.putInt(noOfEntries);

        for (int i = 0; i < noOfEntries; i++) {
            double x = payload.getPeerEntries().get(i).getEciPosition().getX();
            double y = payload.getPeerEntries().get(i).getEciPosition().getY();
            double z = payload.getPeerEntries().get(i).getEciPosition().getZ();

            buffer.putInt(payload.getPeerEntries().get(i).getDeviceId());
            buffer.put(payload.getPeerEntries().get(i).getPeerTypeId());
            buffer.putInt(payload.getPeerEntries().get(i).getPeerIP());
            buffer.putShort(payload.getPeerEntries().get(i).getPeerPort());
            buffer.putDouble(x);
            buffer.putDouble(y);
            buffer.putDouble(z);
            buffer.putLong(payload.getPeerEntries().get(i).getTimestamp());
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

        //Get First 4 + 4 bytes
        payload.setDeviceId(buffer.getInt());
        payload.setNoOfEntries(buffer.getInt());
        payload.setPeerEntries(new ArrayList<>());

        for (int i = 0; i < payload.getNoOfEntries(); i++) {
            DiscoveryResponsePeerEntryPayload peerEntry = new DiscoveryResponsePeerEntryPayload();
            peerEntry.setDeviceId(buffer.getInt());
            peerEntry.setPeerTypeId(buffer.get());
            peerEntry.setPeerIP(buffer.getInt());
            peerEntry.setPeerPort(buffer.getShort());

            ECIPosition eciPosition = new ECIPosition();
            eciPosition.setX(buffer.getDouble());
            eciPosition.setY(buffer.getDouble());
            eciPosition.setZ(buffer.getDouble());

            peerEntry.setEciPosition(eciPosition);
            peerEntry.setTimestamp(buffer.getLong());

            // Add the peer entry
            payload.getPeerEntries().add(peerEntry);
        }

        return payload;
    }
}
