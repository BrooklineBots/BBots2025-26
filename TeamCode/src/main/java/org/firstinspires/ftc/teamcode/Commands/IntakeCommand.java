package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class IntakeCommand extends CommandBase {
  private final Intake intake;
  private final GamepadEx gamepad;

  public IntakeCommand(Intake intake, GamepadEx gamepad) {
    this.intake = intake;
    this.gamepad = gamepad;
    addRequirements(intake);
  }

  public void initialize() {}

  @Override
  public void execute() {
    if (gamepad.isDown(GamepadKeys.Button.A)) {
      intake.intake();
    } else if (gamepad.isDown(GamepadKeys.Button.B)) {
      intake.stop();
    }
  }

  public void end(boolean interrupted) {
    intake.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
