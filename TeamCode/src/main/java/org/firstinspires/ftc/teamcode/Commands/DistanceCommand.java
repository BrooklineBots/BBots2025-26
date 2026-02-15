package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSub;

public class DistanceCommand extends CommandBase {

  private final LimelightSub limelight;
  private final Telemetry telemetry;

  public DistanceCommand(final LimelightSub limelight, final Telemetry telemetry) {
    this.limelight = limelight;
    this.telemetry = telemetry;
    addRequirements(limelight);
  }

  @Override
  public void execute() {
    final double distance = limelight.getDistance();
    final double tx = limelight.getTX();
    final double ta = limelight.getTA();
    final double ty = limelight.getTY();

    telemetry.addData("Has Target", limelight.hasTarget());
    telemetry.addData("Distance", "%.2f", distance);
    telemetry.addData("Tx", "%.2f", tx);
    telemetry.addData("Ty", "%.2f", ty);
    telemetry.addData("Ta", "%.2f", ta);
    telemetry.update();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
