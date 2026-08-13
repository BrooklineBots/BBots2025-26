package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSub;

public class AutoStrafeCommand extends CommandBase {
  private final LimelightSub limelight;
  private final Drivetrain drive;
  private final Telemetry telemetry;

  public AutoStrafeCommand(Drivetrain drive, LimelightSub limelight, Telemetry telemetry) {
    this.limelight = limelight;
    this.drive = drive;
    this.telemetry = telemetry;
    addRequirements(limelight);
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
  }
}
