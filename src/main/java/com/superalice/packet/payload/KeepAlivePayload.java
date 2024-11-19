package com.superalice.packet.payload;

import com.superalice.devicemeta.ECIPosition;
import lombok.Data;

@Data
public class KeepAlivePayload {

    private ECIPosition eciPosition;

}
