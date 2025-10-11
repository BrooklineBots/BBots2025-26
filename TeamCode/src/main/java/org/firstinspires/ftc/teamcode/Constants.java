package org.firstinspires.ftc.teamcode;

public class Constants {

  public static class RobotConstants {
    public static final double ROBOT_MASS = 0.0; // in kilograms
    // pinpoint constants
    public static final double FORWARD_POD_OFFSET = 0.0;
    public static final double STRAFE_POD_OFFSET = 0.0;
  }

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

    public static final double OUTTAKE_MAX_VELOCITY = 9400; // TODO:test max
    // 10178.76; // in radians per minute

    public static final double OUTTAKE_MOVEMENT_SPEED = 9300;
  }

  public static class StorageConstants {
    public static final String STORAGE_SERVO = "storageServo";

    public static final double STORAGE_POWER = 0.5;
  }
}
