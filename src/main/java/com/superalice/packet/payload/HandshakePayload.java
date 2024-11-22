package com.superalice.packet.payload;

import com.superalice.devicemeta.ECIPosition;
import lombok.Data;

@Data
public class HandshakePayload {

    private byte[] hostIPAddress;
    private Byte peerTypeId;
    private ECIPosition eciPosition;

}
