package com.superalice.packet.serdes;

import com.superalice.packet.Packet;
import com.superalice.packet.PacketType;

import java.nio.ByteBuffer;

public class PacketSerDes {

    /**
     * Serialize a packet to a byte array.
     * @param packet
     * @return
     */
    public static byte[] serialize(Packet packet) {
        ByteBuffer buffer = ByteBuffer.allocate(32 + packet.getPayload().length);
        buffer.putInt(packet.getSourceId());
        buffer.putInt(packet.getDestinationId());
        buffer.put(packet.getPacketType().getCode());
        buffer.put(packet.getPriority());
        buffer.putInt(packet.getSequenceNumber());
        buffer.putLong(packet.getTimestamp());
        buffer.putShort(packet.getFragmentIndex());
        buffer.putShort(packet.getTotalFragments());
        buffer.putShort(packet.getCrc());
        buffer.putShort(packet.getReserved());
        buffer.putShort(packet.getPayloadType());
        buffer.put(packet.getPayload());
        return buffer.array();
    }

    public static Packet deserialize(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        Packet packet = new Packet();

        packet.setSourceId(buffer.getInt());
        packet.setDestinationId(buffer.getInt());
        packet.setPacketType(PacketType.getPacketType(buffer.get()));
        packet.setPriority(buffer.get());
        packet.setSequenceNumber(buffer.getInt());
        packet.setTimestamp(buffer.getLong());
        packet.setFragmentIndex(buffer.getShort());
        packet.setTotalFragments(buffer.getShort());
        packet.setCrc(buffer.getShort());
        packet.setReserved(buffer.getShort());
        packet.setPayloadType(buffer.getShort());
        packet.setPayload(bytes);
        return packet;
    }

}
