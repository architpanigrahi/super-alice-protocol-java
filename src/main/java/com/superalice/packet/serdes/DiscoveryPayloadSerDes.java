package com.superalice.packet.serdes;

import com.superalice.devicemeta.ECIPosition;
import com.superalice.packet.payload.DiscoveryPayload;

import java.nio.ByteBuffer;

public class DiscoveryPayloadSerDes implements PayloadSerDes<DiscoveryPayload> {

    /**
     * Serialize DiscoveryPayload
     * Total of 25 bytes -> 1 byte of peer type id and 24 bytes of x, y and z.
     * @param payload
     * @return
     */
    @Override
    public byte[] serialize(DiscoveryPayload payload) {
        byte peerTypeId = payload.getPeerTypeId();
        ECIPosition eciPosition = payload.getEciPosition();
        double x = eciPosition != null && eciPosition.getX() != null ? eciPosition.getX() : 0.0;
        double y = eciPosition != null && eciPosition.getY() != null ? eciPosition.getY() : 0.0;
        double z = eciPosition != null && eciPosition.getZ() != null ? eciPosition.getZ() : 0.0;

        ByteBuffer buffer = ByteBuffer.allocate(25);
        buffer.put(peerTypeId);
        buffer.putDouble(x);
        buffer.putDouble(y);
        buffer.putDouble(z);
        return buffer.array();
    }


    /**
     * Deserialize DiscoveryPayload
     * @param bytes
     * @return
     */
    @Override
    public DiscoveryPayload deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        DiscoveryPayload payload = new DiscoveryPayload();

        byte peerTypeId = buffer.get();
        double x = buffer.getDouble();
        double y = buffer.getDouble();
        double z = buffer.getDouble();

        ECIPosition eciPosition = new ECIPosition();
        eciPosition.setX(x);
        eciPosition.setY(y);
        eciPosition.setZ(z);

        payload.setPeerTypeId(peerTypeId);
        payload.setEciPosition(eciPosition);

        return payload;
    }
}
