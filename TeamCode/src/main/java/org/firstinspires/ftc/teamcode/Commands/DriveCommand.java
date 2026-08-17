package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSub;
import org.firstinspires.ftc.teamcode.Utils.Utils;

public class DriveCommand extends CommandBase {
  private final Drivetrain drive;
  private final GamepadEx gamepad;
  private final LimelightSub limelight;
  private final Telemetry telemetry;
  private final PIDController pid = new PIDController(0.012, 0.0, 0.0001);

  private final double tolerance = 0.1; // Tolerance for joystick input
  private double turnOutput = 0.0;

  public DriveCommand(
      final Drivetrain drive,
      final GamepadEx gamepad,
      final LimelightSub limelight,
      final Telemetry telemetry) {
    this.drive = drive;
    this.gamepad = gamepad;
    this.limelight = limelight;
    this.telemetry = telemetry;
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    pid.reset();
    pid.setTolerance(0.0);
    pid.setSetPoint(0);
  }

  @Override
  public void execute() {
    double rotation = 0.0;
    if (limelight.hasTarget()) {
      double currentTx = limelight.getTx();

      turnOutput = pid.calculate(currentTx);
      // turnOutput = Range.clip(turnOutput, -0.4, 0.4);

      telemetry.addData("Align State", "Target Found");
      telemetry.addData("Limelight Tx", currentTx);
      telemetry.addData("PID Output", turnOutput);
      telemetry.addData("At Setpoint", pid.atSetPoint());

    } else {
      telemetry.addData("Align State", "Searching for Target...");
    }
    telemetry.update();

    if (gamepad.getButton(GamepadKeys.Button.LEFT_BUMPER)
        && Utils.isWithinTolerance(0, gamepad.getRightX(), tolerance)) {
      rotation = turnOutput;

    } else {
      rotation = -gamepad.getRightX() * 0.5;
    }

    if (!Utils.isWithinTolerance(0, gamepad.getLeftY(), tolerance)
        || !Utils.isWithinTolerance(0, gamepad.getLeftX(), tolerance)
        || !Utils.isWithinTolerance(0, rotation, tolerance)) {
      drive.driveFieldCentric(-gamepad.getLeftY(), -gamepad.getLeftX(), rotation);

    } else {
      drive.stopMotors();
    }
  }

  @Override
  public void end(final boolean interrupted) {
    // I think we should keep driving into the wall, what do you think?
    if (interrupted || isFinished()) {
      drive.stopMotors();
    }
  }

  @Override
  public boolean isFinished() {
    return false; // What should we return here? Should this command always run? Or should it stop
    // when the gamepad is released?
  }
}
