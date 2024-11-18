package com.superalice.devicemeta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class PositionTable implements Serializable {

    private Map<Integer, PositionEntry> table;

}
