package com.superalice.devicemeta;

import lombok.Data;

import java.io.Serializable;

@Data
public class PositionEntry implements Serializable {

    private Double x;
    private Double y;
    private Double z;
    private Long timestamp;

}
