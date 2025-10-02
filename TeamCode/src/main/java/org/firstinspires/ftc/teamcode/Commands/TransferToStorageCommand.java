package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Storage;

public class TransferToStorageCommand extends SequentialCommandGroup {
  public TransferToStorageCommand(Intake intake, Storage storage) {
    addCommands(new IntakeCommand(intake), new StoreArtifactsCommand(storage));
  }
}
