package org.firstinspires.ftc.teamcode.Subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

public class LimelightAprilTag extends SubsystemBase {

  private Limelight3A limelight;
  private IMU imu;
  private LLResult latestResult;

  public LimelightAprilTag(HardwareMap hardwareMap) {
    limelight = hardwareMap.get(Limelight3A.class, "limelight");
    limelight.pipelineSwitch(1); // apriltag #1 pipeline

    imu = hardwareMap.get(IMU.class, "imu");
    RevHubOrientationOnRobot revHubOrientationOnRobot =
            new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
    imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));

    limelight.start();
  }

  @Override
  public void periodic() {
    latestResult = limelight.getLatestResult();
  }

  public double getDistance() {
    if (latestResult != null && latestResult.isValid()) {
      return getDistanceFromTag(latestResult.getTa());
    }
    return -1;
  }

  public double getTx() {
    if (latestResult != null && latestResult.isValid()) {
      return latestResult.getTx();
    }
    return 0;
  }

  public double getTy() {
    if (latestResult != null && latestResult.isValid()) {
      return latestResult.getTy();
    }
    return 0;
  }

  public double getTa() {
    if (latestResult != null && latestResult.isValid()) {
      return latestResult.getTa();
    }
    return 0;
  }

  public boolean hasTarget() {
    return latestResult != null && latestResult.isValid();
  }

  private double getDistanceFromTag(double ta) {
    double scale = 400; // TODO: Calibrate this (once mounted on the robot)
    double distance = (scale / ta);
    return distance;
  }
}