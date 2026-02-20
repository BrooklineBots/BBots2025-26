package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Subsystems.Endgame;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class RaiseEndgameCommand extends CommandBase {
    private final Endgame endgame;

    public RaiseEndgameCommand(Endgame endgame) {
        this.endgame = endgame;
        addRequirements(endgame);
    }

    @Override
    public void execute() {
        endgame.raise();
    }

    @Override
    public void end(boolean interrupted) {
        endgame.stopEndgame();
    }
}