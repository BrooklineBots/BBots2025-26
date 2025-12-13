package org.firstinspires.ftc.teamcode;

public class Constants {

  public static class RobotConstants {
    public static final double ROBOT_MASS = 8.5; // in kilograms
    // pinpoint constants
    public static final double FORWARD_POD_OFFSET = 3.125;
    public static final double STRAFE_POD_OFFSET = -1.625;
  }

  public static class DriveConstants {
    public static final String FRONT_LEFT_MOTOR_ID = "frontLeftMotor";
    public static final String FRONT_RIGHT_MOTOR_ID = "frontRightMotor";
    public static final String BACK_LEFT_MOTOR_ID = "backLeftMotor";
    public static final String BACK_RIGHT_MOTOR_ID = "backRightMotor";

    public static final String IMU_ID = "imu";
  }

  public static class IntakeConstants {
    public static final String INTAKE_ID = "intakeMotor";

    public static final double INTAKE_VELOCITY = 900;
    public static final double EXPEL_VELOCITY = 700;
    public static final double INTAKE_MAX_VELOCITY = 900; // in RPM, 312
  }

  public static class OuttakeConstants {

    private static double rpmToRadPerSec(double rpm) {
      return rpm * (2 * Math.PI / 60);
    }

    public static final String OUTTAKE_ID = "outtakeMotor";

    public static final double OUTTAKE_MAX_VELOCITY = rpmToRadPerSec(12000);
    public static final double OUTTAKE_MOVEMENT_SPEED = rpmToRadPerSec(2000);
    public static final double OUTTAKE_FAST_SPEED = rpmToRadPerSec(4000);
  }

  public static class StorageConstants {
    public static final String STORAGE_MOTOR_ID = "storageMotor";

    public static final double STORAGE_VELOCITY = 1400;
    public static final double STORAGE_EXPEL_VELOCITY = 900;
    public static final double STORAGE_MAX_VELOCITY = 1400;
  }

  public static class BombshellServoConstants {
    public static final String BOMBSHELL_SERVO_ID = "bombshellServo";
    public static final double servoPosition1 = 0;
    public static final double servoPosition2 = 1;
    public static final double servoPosition3 = 0.2;
    public static final long secondsToPush = 830;
  }
}
