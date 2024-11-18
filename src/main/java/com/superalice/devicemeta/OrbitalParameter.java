package com.superalice.devicemeta;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
public class OrbitalParameter implements Serializable {

    private Double semiMajorAxis;
    private Double eccentricity;
    private Double inclination;
    private Double argOfPerigee;
    private Double raan;
    private Double meanAnomalyAtEpoch;
    private Double epochTime;

}
