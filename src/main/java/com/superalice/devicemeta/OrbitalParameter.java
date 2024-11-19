package com.superalice.devicemeta;

import lombok.*;

import java.io.Serializable;

@Data
public class OrbitalParameter implements Serializable {

    private Double semiMajorAxis;
    private Double eccentricity;
    private Double inclination;
    private Double argOfPerigee;
    private Double raan;
    private Double meanAnomalyAtEpoch;
    private Double epochTime;

}
