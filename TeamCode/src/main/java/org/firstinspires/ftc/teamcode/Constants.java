package org.firstinspires.ftc.teamcode;

public class Constants {
  public static class DriveConstants {
    public static final String FRONT_LEFT_MOTOR_ID = "frontLeftMotor";
    public static final String FRONT_RIGHT_MOTOR_ID = "frontRightMotor";
    public static final String BACK_LEFT_MOTOR_ID = "backLeftMotor";
    public static final String BACK_RIGHT_MOTOR_ID = "backRightMotor";

    public static final String IMU_ID = "imu";
  }

  public static class IntakeConstants {
    public static final String LEFT_INTAKE_ID = "leftIntakeMotor";
    public static final String RIGHT_INTAKE_ID = "rightIntakeMotor";

    public static final double INTAKE_VELOCITY = 170;
    public static final double INTAKE_MAX_VELOCITY = 312; // in RPM
  }

  public static class OuttakeConstants {
    public static final String LEFT_OUTTAKE_ID = "leftOuttakeMotor";
    public static final String RIGHT_OUTTAKE_ID = "rightOuttakeMotor";

    public static final double OUTTAKE_MAX_VELOCITY = 11614; // in RPM
  }

  public static class StorageConstants {
    public static final String STORAGE_SERVO = "storageServo";

    public static final double STORAGE_POWER = 0.5;
  }
}
