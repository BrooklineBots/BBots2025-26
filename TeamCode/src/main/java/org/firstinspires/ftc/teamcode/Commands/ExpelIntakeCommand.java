package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class ExpelIntakeCommand extends CommandBase {
    private final Intake intake;

    public ExpelIntakeCommand(Intake intake) {
        this.intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.expel();
    }

    @Override
    public void end(boolean interrupted) {
        intake.stop();
    }
}