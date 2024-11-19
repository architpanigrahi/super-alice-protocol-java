package com.superalice.packet.serdes;

import com.superalice.packet.payload.RetransmitPayload;

import java.nio.ByteBuffer;

public class RetransmitPayloadSerDes implements PayloadSerDes<RetransmitPayload> {

    /**
     * Serialize RetransmitPayload
     * total bytes = 4 (sequence number ) + 2 (fragment_id) = 6 bytes
     * @param payload
     * @return
     */
    @Override
    public byte[] serialize(RetransmitPayload payload) {
        ByteBuffer buffer = ByteBuffer.allocate(6);
        buffer.putInt(payload.getSequenceNumber());
        buffer.putShort(payload.getFragmentIndex());
        return buffer.array();
    }

    /**
     * Deserialize RetransmitPayload
     * @param bytes
     * @return
     */
    @Override
    public RetransmitPayload deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        RetransmitPayload payload = new RetransmitPayload();
        payload.setSequenceNumber(buffer.getInt());
        payload.setFragmentIndex(buffer.getShort());
        return payload;
    }
}
