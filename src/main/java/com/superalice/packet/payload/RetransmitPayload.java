package com.superalice.packet.payload;

import lombok.Data;

@Data
public class RetransmitPayload {

    private Integer sequenceNumber;
    private Short fragmentIndex;

}
