package com.superalice.packet.serdes;

import com.superalice.devicemeta.ECIPosition;
import com.superalice.packet.payload.HandshakePayload;

import java.nio.ByteBuffer;

public class HandshakePayloadSerDes implements PayloadSerDes<HandshakePayload> {

    /**
     * Serialize HandshakePayload
     * Total of 31 bytes -> 4 bytes of IP host, 2 bytes for port, 1 byte for peerTypeId and 24 bytes for coordinates
     * @param payload
     * @return
     */
    @Override
    public byte[] serialize(HandshakePayload payload) {
        byte[] hostIPAddress = payload.getHostIPAddress();
        byte peerTypeId = payload.getPeerTypeId();

        ECIPosition eciPosition = payload.getEciPosition();
        double x = eciPosition != null && eciPosition.getX() != null ? eciPosition.getX() : 0.0;
        double y = eciPosition != null && eciPosition.getY() != null ? eciPosition.getY() : 0.0;
        double z = eciPosition != null && eciPosition.getZ() != null ? eciPosition.getZ() : 0.0;

        ByteBuffer buffer = ByteBuffer.allocate(31);
        buffer.put(hostIPAddress);
        buffer.put(peerTypeId);
        buffer.putDouble(x);
        buffer.putDouble(y);
        buffer.putDouble(z);

        return buffer.array();
    }

    /**
     * Deserialize HandshakePayload
     * @param bytes
     * @return
     */
    @Override
    public HandshakePayload deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        HandshakePayload payload = new HandshakePayload();

        byte[] hostIPBytes = new byte[6];
        buffer.get(hostIPBytes);
        payload.setHostIPAddress(hostIPBytes);
        payload.setPeerTypeId(buffer.get());

        ECIPosition eciPosition = new ECIPosition();
        eciPosition.setX(buffer.getDouble());
        eciPosition.setY(buffer.getDouble());
        eciPosition.setZ(buffer.getDouble());
        payload.setEciPosition(eciPosition);


        return payload;
    }
}
