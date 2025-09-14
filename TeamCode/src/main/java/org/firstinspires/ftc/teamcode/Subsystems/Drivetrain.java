package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.drivebase.MecanumDrive;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.hardware.RevIMU;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import org.firstinspires.ftc.teamcode.Constants;

public class Drivetrain extends SubsystemBase {
  // Declare our motors
  private final Motor frontLeftMotor;
  private final Motor backLeftMotor;
  private final Motor frontRightMotor;
  private final Motor backRightMotor;

  private final RevIMU revIMU;

  private double fieldHeadingOffset = 0.0; // in radians

  private final HardwareMap hwMap;

  private MecanumDrive drive = null;

  // Make sure your ID's match your configuration
  public Drivetrain(final HardwareMap hwMap) {
    this.hwMap = hwMap;

    frontLeftMotor = new Motor(hwMap, Constants.DriveConstants.FRONT_LEFT_MOTOR_ID);
    backLeftMotor = new Motor(hwMap, Constants.DriveConstants.BACK_LEFT_MOTOR_ID);
    frontRightMotor = new Motor(hwMap, Constants.DriveConstants.FRONT_RIGHT_MOTOR_ID);
    backRightMotor = new Motor(hwMap, Constants.DriveConstants.BACK_RIGHT_MOTOR_ID);

    frontLeftMotor.setInverted(true); // Invert this motor!
    backLeftMotor.setInverted(true); // Invert this motor!

    frontLeftMotor.setRunMode(
        Motor.RunMode
            .VelocityControl); // Set the run mode for the motors! Read the docs if you don't know
    // what this is or what to do here!
    frontRightMotor.setRunMode(Motor.RunMode.VelocityControl);
    backLeftMotor.setRunMode(Motor.RunMode.VelocityControl);
    backRightMotor.setRunMode(Motor.RunMode.VelocityControl);

    // Retrieve the IMU from the hardware map
    revIMU = new RevIMU(hwMap, Constants.DriveConstants.IMU_ID); // Constants.DriveConstants.IMU_ID

    revIMU.init(); // FIXME: The orientation is very likely wrong. Needs tested.

    /* Old parameters settings */
    // Adjust the orientation parameters to match your robot
    //    IMU.Parameters parameters =
    //        new IMU.Parameters(
    //            new RevHubOrientationOnRobot(
    //                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
    //                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));

    drive =
        new MecanumDrive(
            frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor); // Read the docs
  }

  public void resetYaw() {
    revIMU.reset();
    setFieldHeadingOffset(0);
  }

  public double getBotHeading() {
    final double botHeading = revIMU.getHeading() - fieldHeadingOffset;
    return botHeading;
  }

  public void setFieldHeadingOffset(final double newOffset) {
    fieldHeadingOffset = newOffset;
  }

  public void stopMotors() { // Stops all motors
    drive.stop();
  }

  public void driveRobotCentric(final GamepadEx Controller) {
    driveRobotCentric(-Controller.getLeftY(), Controller.getLeftX(), Controller.getRightX());
  }

  public void driveRobotCentric(final double forward, final double strafe, final double rotate) {
    drive.driveRobotCentric(strafe, forward, rotate);
  }

  public void driveFieldCentric(final GamepadEx Controller) {
    driveFieldCentric(-Controller.getLeftY(), Controller.getLeftX(), Controller.getRightX());
  }

  public void driveFieldCentric(final double forward, final double strafe, final double rotate) {

    drive.driveFieldCentric(strafe, forward, rotate, revIMU.getRotation2d().getDegrees(), false);
  }
}
