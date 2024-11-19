package com.superalice.devicemeta;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class PositionTable implements Serializable {

    private Map<Integer, PositionEntry> table = new HashMap<>();

}
