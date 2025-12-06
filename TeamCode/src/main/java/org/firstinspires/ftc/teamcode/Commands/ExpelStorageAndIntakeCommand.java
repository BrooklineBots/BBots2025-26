package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Storage;

public class ExpelStorageAndIntakeCommand extends SequentialCommandGroup {
  public ExpelStorageAndIntakeCommand(final Intake intake, final Storage storage) {
    addCommands(new ExpelIntakeCommand(intake), new ExpelArtifactsFromStorageCommand(storage));
  }
}
