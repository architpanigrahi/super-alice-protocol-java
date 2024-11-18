package com.superalice.devicemeta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class ECIPosition implements Serializable {

    private Double x;
    private Double y;
    private Double z;
}
