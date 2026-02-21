package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.Endgame;

public class LowerEndgameCommand extends CommandBase {
  private final Endgame endgame;

  public LowerEndgameCommand(Endgame endgame) {
    this.endgame = endgame;
    addRequirements(endgame);
  }

  @Override
  public void execute() {
    endgame.lower();
  }

  @Override
  public void end(boolean interrupted) {
    endgame.stopEndgame();
  }
}
