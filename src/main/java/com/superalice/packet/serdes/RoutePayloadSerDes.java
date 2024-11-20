package com.superalice.packet.serdes;

import com.superalice.packet.payload.RoutePayload;

import java.nio.ByteBuffer;

public class RoutePayloadSerDes implements PayloadSerDes<RoutePayload> {
    @Override
    public byte[] serialize(RoutePayload payload) {
        int routeLength = payload.getRoutePeers().size();
        ByteBuffer buffer = ByteBuffer.allocate(routeLength * 4);

        for (int i = 0; i < routeLength; i++) {
            buffer.putInt(payload.getRoutePeers().get(i));
        }

        return buffer.array();
    }

    @Override
    public RoutePayload deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        RoutePayload payload = new RoutePayload();

        while (buffer.hasRemaining()) {
            payload.getRoutePeers().add(buffer.getInt());
        }
        return payload;
    }
}
