package com.superalice.packet.serdes;

import com.superalice.packet.payload.PullPayload;

import java.nio.ByteBuffer;

public class PullPayloadSerDes implements PayloadSerDes<PullPayload> {

    /**
     * Serialize PullPayload
     * 4 (host) + 2 (port) = 6 bytes
     * @param payload
     * @return
     */
    @Override
    public byte[] serialize(PullPayload payload) {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        buffer.put(payload.getHostIPAddress());
        return buffer.array();
    }

    /**
     * Deserialize PullPayload
     * @param bytes
     * @return
     */
    @Override
    public PullPayload deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        PullPayload payload = new PullPayload();
        byte[] hostIPBytes = new byte[6];
        buffer.get(hostIPBytes);
        payload.setHostIPAddress(hostIPBytes);
        return payload;
    }
}
