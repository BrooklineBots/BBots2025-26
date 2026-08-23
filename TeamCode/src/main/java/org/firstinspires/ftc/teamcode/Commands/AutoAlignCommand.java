package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSub;

public class AutoAlignCommand extends CommandBase {


  private final LimelightSub limelight;
  private final Drivetrain drive;
  private final Telemetry telemetry;
  private final PIDController pid = new PIDController(0.012, 0.0, 0.0001);
  private LimelightSub.Pipeline initialPipeline;

  public AutoAlignCommand(Drivetrain drive, LimelightSub limelight, Telemetry telemetry, RobotContainer.alliance alliance) {
    this.limelight = limelight;
    this.drive = drive;
    this.telemetry = telemetry;
    addRequirements(limelight, drive);
    initialPipeline = limelight.getPipeline();
    switch(alliance){
        case Blue:
            limelight.switchPipeline(LimelightSub.Pipeline.BLUEGOAL);
        case Red:
            limelight.switchPipeline(LimelightSub.Pipeline.REDGOAL);
        default:
            limelight.switchPipeline(LimelightSub.Pipeline.BLUEGOAL);
  }
  }

  @Override
  public void initialize() {
    pid.reset();
    pid.setTolerance(0.0);
    pid.setSetPoint(0);
  }

  @Override
  public void execute() {
    if (limelight.hasTarget()) {
      double currentTx = limelight.getTx();

      double turnOutput = pid.calculate(currentTx);
      turnOutput = Range.clip(turnOutput, -0.4, 0.4);

      drive.driveFieldCentric(0, 0, turnOutput);

      telemetry.addData("Align State", "Target Found");
      telemetry.addData("Limelight Tx", currentTx);
      telemetry.addData("PID Output", turnOutput);
      telemetry.addData("At Setpoint", pid.atSetPoint());

    } else {
      telemetry.addData("Align State", "Searching for Target...");
    }
    telemetry.update();
  }

  @Override
  public boolean isFinished() {
    // return limelight.hasTarget() && pid.atSetPoint();
    return false;
  }

  @Override
  public void end(boolean interrupted) {
    drive.stopMotors();
    telemetry.addData("Align State", interrupted ? "Interrupted" : "Complete");
    telemetry.update();
  }
}
