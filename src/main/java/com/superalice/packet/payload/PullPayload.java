package com.superalice.packet.payload;

import lombok.Data;

@Data
public class PullPayload {

    private Integer hostIP;
    private Short hostPort;

}
