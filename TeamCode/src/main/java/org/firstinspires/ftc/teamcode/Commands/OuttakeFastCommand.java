package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake;

public class OuttakeFastCommand extends CommandBase {
  private final Outtake outtake;

  public OuttakeFastCommand(final Outtake outtake) {
    this.outtake = outtake;
    addRequirements(outtake);
  }

  @Override
  public void execute() {
    outtake.shootFast();
  }

  @Override
  public void end(final boolean interrupted) {
    outtake.stop();
  }
}
