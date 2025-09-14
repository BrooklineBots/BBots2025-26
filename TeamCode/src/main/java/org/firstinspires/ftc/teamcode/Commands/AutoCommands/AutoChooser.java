package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RobotContainer;
import org.firstinspires.ftc.teamcode.RobotContainer.AutoMode;

public class AutoChooser
    extends CommandBase { // Note that I'm still working on this. It does not work yet. Feel free to
  // mess around with it.

  private final AutoMode[] modes = AutoMode.values();
  ;
  private final Telemetry telemetry;
  private final GamepadEx gamepad;
  private int selectedIndex = 0;
  private AutoMode selected;

  private final RobotContainer robotContainer;

  public AutoChooser(
      final RobotContainer robotContainer,
      final GamepadEx gamepad,
      final Telemetry telemetry) { // Note that I'm still working on this. It does not work yet.
    this.robotContainer = robotContainer;
    this.gamepad = gamepad;
    this.telemetry = telemetry;
  }

  @Override
  public void execute() { // Note that I'm still working on this. It does not work yet.
    if (gamepad.isDown(GamepadKeys.Button.DPAD_RIGHT)) {
      selectedIndex = (selectedIndex + 1) % modes.length;
      new WaitCommand(300).schedule();
    } else if (gamepad.isDown(GamepadKeys.Button.DPAD_LEFT)) {
      selectedIndex = (selectedIndex - 1 + modes.length) % modes.length;
      new WaitCommand(300).schedule();
    }

    new SequentialCommandGroup(
        new InstantCommand(() -> telemetry.addLine("Use D-Pad to choose autonomous mode:")),
        new InstantCommand(() -> telemetry.addLine("Selected: " + modes[selectedIndex])),
        new InstantCommand(telemetry::update));
    telemetry.speak("Hello");

    selected = modes[selectedIndex];
  }

  @Override
  public void end(final boolean interrupted) {
    telemetry.addData("Selected Auto Mode", selected);
    telemetry.update();
  }

  public AutoMode getSelectedMode() {
    return selected;
  }

  @Override
  public boolean isFinished() {
    return robotContainer.getJavaBot().isStarted() || robotContainer.getJavaBot().isStopRequested();
  }
}
