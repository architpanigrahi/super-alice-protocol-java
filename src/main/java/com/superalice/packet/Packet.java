package com.superalice.packet;

import lombok.Data;

@Data
public class Packet {

    private Integer sourceId;
    private Integer destinationId;
    private PacketType packetType;
    private Byte priority;
    private Integer sequenceNumber;
    private Long timestamp;
    private Short fragmentIndex;
    private Short totalFragments;
    private Short crc;
    private Short reserved;
    private Short payloadType;
    private byte[] payload;

}
