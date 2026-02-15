package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;

public class LimelightSub extends SubsystemBase {
  private final Limelight3A limelight;
  private LLResult latestResult;

  public LimelightSub(HardwareMap hardwareMap) {
    // Initialize the hardware using the official SDK class [1]
    limelight = hardwareMap.get(Limelight3A.class, "limelight");

    // pipleine 0 is for apriltags, color is 11&10
    limelight.pipelineSwitch(0);

  }

  @Override
  public void periodic() {

    latestResult = limelight.getLatestResult();
  }



  public boolean hasTarget() {
    // Check if the result exists and if it contains a valid detection [1, 4]
    return latestResult != null && latestResult.isValid();
  }

  public double getTX() {
    return hasTarget() ? latestResult.getTx() : 0.0;
  }

  public double getTA() {
    return hasTarget() ? latestResult.getTa() : 0.0;
  }

  public double getTY() {
    return hasTarget() ? latestResult.getTy() : 0.0;
  }


  public void setPipeline(int index) {
    limelight.pipelineSwitch(index);
  }

  public double getDistance() {
    return hasTarget() ? latestResult.getTa() : (0.0);
  }
}
