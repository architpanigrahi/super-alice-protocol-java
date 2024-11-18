package com.superalice.devicemeta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class PositionEntry implements Serializable {

    private Double x;
    private Double y;
    private Double z;
    private Long timestamp;

}
