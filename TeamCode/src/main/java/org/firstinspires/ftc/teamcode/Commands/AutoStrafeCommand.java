package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSub;

public class AutoStrafeCommand extends CommandBase {
  private final LimelightSub limelight;
  private final Drivetrain drive;
  private final Telemetry telemetry;
  private LimelightSub.Pipeline initialPipeline;

  public AutoStrafeCommand(
      Drivetrain drive,
      LimelightSub limelight,
      Telemetry telemetry,
      RobotContainer.alliance alliance) {
    this.limelight = limelight;
    this.drive = drive;
    this.telemetry = telemetry;
    addRequirements(limelight);
    initialPipeline = limelight.getPipeline();
    switch (alliance) {
      case Blue:
        limelight.switchPipeline(LimelightSub.Pipeline.BLUEGOAL);
      case Red:
        limelight.switchPipeline(LimelightSub.Pipeline.REDGOAL);
      default:
        limelight.switchPipeline(LimelightSub.Pipeline.BLUEGOAL);
    }
  }

  @Override
  public void execute() {
    telemetry.addData("TX", limelight.getTx());
    double tolerance = 4.0;
    if (!limelight.hasTarget()) {
      telemetry.addData("Target Result", "No target found");
    } else if (limelight.getTx() < -tolerance) {
      drive.driveRobotCentric(0, 0.25, 0);
      telemetry.addData("Target Result", "to robot's left");
    } else if (limelight.getTx() > tolerance) {
      drive.driveRobotCentric(0, -0.25, 0);
      telemetry.addData("Target Result", "to robot's right");
    } else {
      telemetry.addData("Target Result", "spot on");
      drive.driveRobotCentric(0, 0, 0);
    }
    telemetry.update();
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopMotors();
    limelight.switchPipeline(initialPipeline);
  }
}
