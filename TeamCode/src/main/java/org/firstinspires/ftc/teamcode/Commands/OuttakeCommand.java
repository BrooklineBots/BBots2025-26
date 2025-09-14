package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake;

public class OuttakeCommand extends CommandBase {
  private final Outtake outtake;
  private final GamepadEx gamepad;

  public OuttakeCommand(final Outtake outtake, final GamepadEx gamepad) {
    this.outtake = outtake;
    this.gamepad = gamepad;
    addRequirements(outtake);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    if (gamepad.isDown(GamepadKeys.Button.X)) {
      outtake.shoot();
    } else if (gamepad.isDown(GamepadKeys.Button.Y)) {
      outtake.shoot();
    }
  }

  @Override
  public void end(final boolean interrupted) {
    outtake.stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
