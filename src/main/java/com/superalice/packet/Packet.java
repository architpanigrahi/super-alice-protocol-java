package com.superalice.packet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Packet {

    private Integer sourceId;
    private Integer destinationId;
    private PacketType packetType;
    private Byte priority;
    private Integer sequenceNumber;
    private Long timestamp;
    private Short fragmentId;
    private Short fragmentIndex;
    private Short totalFragments;
    private Short crc;
    private Short reserved;
    private Short payloadType;
    private List<Byte> payload;

}
