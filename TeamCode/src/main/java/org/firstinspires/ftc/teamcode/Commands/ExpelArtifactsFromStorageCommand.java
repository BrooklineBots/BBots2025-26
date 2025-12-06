package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.Storage;

public class ExpelArtifactsFromStorageCommand extends CommandBase {
  private final Storage storage;

  public ExpelArtifactsFromStorageCommand(final Storage storage) {
    this.storage = storage;
    addRequirements(storage);
  }

  @Override
  public void execute() {
    storage.expelStorage();
  }

  @Override
  public void end(final boolean interrupted) {
    storage.stop();
  }
}
