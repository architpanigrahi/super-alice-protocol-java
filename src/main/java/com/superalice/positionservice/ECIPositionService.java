package com.superalice.positionservice;

import com.superalice.devicemeta.ECIPosition;
import com.superalice.devicemeta.OrbitalParameter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ECIPositionService {


    public static ECIPosition calculatePosition(OrbitalParameter orbitalParameter) {
        // Constants
        final double mu = 3.986004418e14; // Standard gravitational parameter for Earth (m^3/s^2)

        // Extract parameters
        double a = orbitalParameter.getSemiMajorAxis(); // Semi-major axis
        double e = orbitalParameter.getEccentricity(); // Eccentricity
        double i = Math.toRadians(orbitalParameter.getInclination()); // Inclination in radians
        double omega = Math.toRadians(orbitalParameter.getArgOfPerigee()); // Argument of perigee in radians
        double raan = Math.toRadians(orbitalParameter.getRaan()); // Right Ascension of Ascending Node in radians
        double M = Math.toRadians(orbitalParameter.getMeanAnomalyAtEpoch()); // Mean anomaly in radians

        // Solve Kepler's Equation to get Eccentric Anomaly (E)
        double E = solveKeplersEquation(M, e);

        // Calculate true anomaly (ν)
        double nu = 2 * Math.atan2(Math.sqrt(1 + e) * Math.sin(E / 2), Math.sqrt(1 - e) * Math.cos(E / 2));

        // Calculate orbital radius (r)
        double r = a * (1 - e * Math.cos(E));

        // Position in orbital plane
        double xOrbital = r * Math.cos(nu);
        double yOrbital = r * Math.sin(nu);

        // Transform to ECI frame
        double cosRAAN = Math.cos(raan);
        double sinRAAN = Math.sin(raan);
        double cosI = Math.cos(i);
        double sinI = Math.sin(i);
        double cosOmega = Math.cos(omega);
        double sinOmega = Math.sin(omega);

        // Rotation matrices
        double xECI = (cosRAAN * cosOmega - sinRAAN * sinOmega * cosI) * xOrbital +
                (-cosRAAN * sinOmega - sinRAAN * cosOmega * cosI) * yOrbital;
        double yECI = (sinRAAN * cosOmega + cosRAAN * sinOmega * cosI) * xOrbital +
                (-sinRAAN * sinOmega + cosRAAN * cosOmega * cosI) * yOrbital;
        double zECI = (sinI * sinOmega) * xOrbital + (sinI * cosOmega) * yOrbital;

        // Construct and return ECIPosition
        ECIPosition position = new ECIPosition();
        position.setX(xECI);
        position.setY(yECI);
        position.setZ(zECI);

        return position;
    }


    public static Double calculateDistance(ECIPosition eciPosition1, ECIPosition eciPosition2) {
        double dx = eciPosition1.getX() - eciPosition2.getX();
        double dy = eciPosition1.getY() - eciPosition2.getY();
        double dz = eciPosition1.getZ() - eciPosition2.getZ();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }


    private static double solveKeplersEquation(double M, double e) {
        final double tolerance = 1e-6; // Convergence tolerance
        double E = M; // Initial guess
        double deltaE;

        do {
            deltaE = (M - (E - e * Math.sin(E))) / (1 - e * Math.cos(E));
            E += deltaE;
        } while (Math.abs(deltaE) > tolerance);

        return E;
    }
}
