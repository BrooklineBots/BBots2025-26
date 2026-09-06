package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import org.firstinspires.ftc.teamcode.Constants;

public class LimelightSub extends SubsystemBase {

  private Limelight3A limelight;
  private LLResult latestResult;

  public enum Pipeline {
    OBELISK,
    REDGOAL,
    BLUEGOAL,
    COLOR
  }

  public LimelightSub(HardwareMap hardwareMap) {
    limelight = hardwareMap.get(Limelight3A.class, "limelight");
    limelight.setPollRateHz(100); // limelight pipleine
    switchPipeline(Pipeline.OBELISK);
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

  public Pipeline getPipeline() {
    switch (limelight.getStatus().getPipelineIndex()) {
      case 9:
        return Pipeline.BLUEGOAL;
      case 8:
        return Pipeline.REDGOAL;
      case 7:
        return Pipeline.OBELISK;
      case 6:
        return Pipeline.COLOR;
      default:
        return Pipeline.BLUEGOAL;
    }
  }

  public void switchPipeline(Pipeline pipeline) {
    int n = 0;

    switch (pipeline) {
      case BLUEGOAL:
        n = 9;
        break;
      case REDGOAL:
        n = 8;
        break;
      case OBELISK:
        n = 7;
        break;
      case COLOR:
        n = 6;
        break;
    }
    limelight.pipelineSwitch(n);
  }

  private double getDistanceFromTag() {
    return (Constants.LimelightConstants.aprilTagHeight
            - Constants.LimelightConstants.limelightHeight)
        / Math.tan(Constants.LimelightConstants.limelightAngle);
  }

  public double getOuttakeSpeed() {
    // add in calculations based on the treemap
  }
}
