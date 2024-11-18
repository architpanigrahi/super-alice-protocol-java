package com.superalice.devicemeta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class DeviceIPTable implements Serializable {

    private Map<Integer, String> table;
}
