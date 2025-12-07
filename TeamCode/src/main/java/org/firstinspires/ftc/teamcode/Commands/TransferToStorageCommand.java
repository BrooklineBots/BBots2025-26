package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Storage;

public class TransferToStorageCommand extends SequentialCommandGroup {
  public TransferToStorageCommand(final Intake intake, final Storage storage) {
    addCommands(intakeAndStore(intake, storage));
  }

  private static ParallelCommandGroup intakeAndStore(final Intake intake, final Storage storage) {
    return new ParallelCommandGroup(new IntakeCommand(intake), new StoreArtifactsCommand(storage));
  }
}
