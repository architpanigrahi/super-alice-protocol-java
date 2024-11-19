package com.superalice.packet.payload;

import com.superalice.devicemeta.ECIPosition;
import lombok.Data;

@Data
public class HandshakePayload {

    private Integer hostIP;
    private Short hostPort;
    private Byte peerTypeId;
    private ECIPosition eciPosition;

}
