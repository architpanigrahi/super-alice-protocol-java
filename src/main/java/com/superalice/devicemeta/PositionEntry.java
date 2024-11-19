package com.superalice.devicemeta;

import lombok.Data;

import java.io.Serializable;

@Data
public class PositionEntry implements Serializable {

    private ECIPosition eciPosition;
    private Long timestamp;

}
