package com.superalice.packet.serdes;

import com.superalice.devicemeta.ECIPosition;
import com.superalice.packet.payload.KeepAlivePayload;

import java.nio.ByteBuffer;

public class KeepAlivePayloadSerDes implements PayloadSerDes<KeepAlivePayload> {

    /**
     * Serialize KeepAlivePayload
     * total bytes = 8 (x) + 8 (y) + 8 (z) = 24 bytes
     * @param payload
     * @return
     */
    @Override
    public byte[] serialize(KeepAlivePayload payload) {
        ByteBuffer buffer = ByteBuffer.allocate(24);
        buffer.putDouble(payload.getEciPosition().getX());
        buffer.putDouble(payload.getEciPosition().getY());
        buffer.putDouble(payload.getEciPosition().getZ());

        return buffer.array();
    }

    /**
     * Deserialize KeepAlivePayload
     * @param bytes
     * @return
     */
    @Override
    public KeepAlivePayload deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        KeepAlivePayload payload = new KeepAlivePayload();
        ECIPosition eciPosition = new ECIPosition();

        eciPosition.setX(buffer.getDouble());
        eciPosition.setY(buffer.getDouble());
        eciPosition.setZ(buffer.getDouble());
        payload.setEciPosition(eciPosition);

        return payload;
    }
}
