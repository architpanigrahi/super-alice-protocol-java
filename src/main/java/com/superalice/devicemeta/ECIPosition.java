package com.superalice.devicemeta;

import lombok.*;

import java.io.Serializable;

@Data
public class ECIPosition implements Serializable {

    private Double x;
    private Double y;
    private Double z;
}
