// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    /**
     * Shows the CAN IDs of all the motors
     */
    public static class Port {
        public static final int BACK_LEFT_DRIVE = 1;
        public static final int FRONT_LEFT_DRIVE = 4;
        public static final int FRONT_RIGHT_DRIVE = 2;
        public static final int BACK_RIGHT_DRIVE = 3;

        // robot has not been built so change these later
        public static final int INTAKE = -1;
        public static final int INTAKE_HINGE = -1;
        public static final int SHOOTER = -1;
        public static final int CONVEYOR = -1;
    }

    /**
     * Multiplies the values when driving
     * To avoid runtime errors, do not set these fields above 1
     */
    public static class Speed {
        public static final double AUTO = .6;
        public static final double TELEOP = 1;
        public static final double TELEOP_ROTATION = 1;
        public static final double SHOOTER = 5;
        public static final double CONVEYOR = 1.5;
   }

    public static class OperatorConstants {
        public static final int DRIVER_CONTROLLER_PORT = 0;
    }
}
