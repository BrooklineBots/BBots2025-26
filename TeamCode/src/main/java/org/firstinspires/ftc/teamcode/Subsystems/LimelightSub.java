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

    // Use the correct method to set the pipeline (e.g., pipeline 1 for AprilTags)
    // Note: .pipelineSwitch() does not exist in the official SDK [1]
    limelight.pipelineSwitch(1);

    // The Limelight starts automatically upon initialization;
    // there is no .start() or .setPollRateHz() method [1]
  }

  @Override
  public void periodic() {
    // Frequency is determined by the robot's loop speed and Limelight hardware [1-3]
    latestResult = limelight.getLatestResult();
  }

  /**
   * Checks if the camera currently has a valid target.
   */
  public boolean hasTarget() {
    // Check if the result exists and if it contains a valid detection [1, 4]
    return latestResult != null && latestResult.isValid();
  }

  /**
   * Returns the horizontal offset (TX) to the target.
   * This is used as the 'bearing' for auto-rotation logic [4-6].
   */
  public double getTX() {
    return hasTarget() ? latestResult.getTx() : 0.0;
  }

  public double getTA() {
    return hasTarget() ? latestResult.getTa() : 0.0;
  }

  public double getTY() {
    return hasTarget() ? latestResult.getTy() : 0.0;
  }


  /**
   * Allows commands to switch between different pre-configured pipelines.
   * For example: switching from AprilTag tracking to Color Blob tracking [9].
   */
  public void setPipeline(int index) {
    limelight.pipelineSwitch(index);
  }

  public double getDistance() {
    return hasTarget() ? latestResult.getTa() : (0.0);
  }
}
