package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoChooser;

public class RobotContainer {
  // Subsystems


  // Dependencies
  private final HardwareMap hardwareMap;
  private final Telemetry telemetry;
  private GamepadEx gamepad1;
  private GamepadEx gamepad2;
  private final CommandOpMode JavaBot;

  public enum gameMode {
    Auto,
    TeleOp
  }

  private gameMode currentGameMode = null;

  public enum AutoMode { // Enum of all valid autonomous modes
    ExampleAuto,
    DoNothingAuto;
  }

  public RobotContainer(
      HardwareMap hardwareMap,
      Gamepad gamepad1,
      Gamepad gamepad2,
      Telemetry telemetry,
      CommandOpMode JavaBot) {
    this.hardwareMap = hardwareMap;
    this.gamepad1 = new GamepadEx(gamepad1);
    this.gamepad2 = new GamepadEx(gamepad2);
    this.telemetry = telemetry;
    this.JavaBot = JavaBot;
  }

  public CommandOpMode getJavaBot() {
    return JavaBot;
  }

  public void initializeSubsystems() {

    // Register subsystems with scheduler
    CommandScheduler.getInstance()
        .registerSubsystem(
           ExampleSubsystem.getInstance()
                );
  }

  public void configureTeleOp() {
    currentGameMode = gameMode.TeleOp;
    initializeSubsystems();

    // Default commands

    // Button bindings
    configureButtonBindings();
  }

  public void configureAuto() { //Note that I'm still working on this. It does not work yet.
    currentGameMode = gameMode.Auto;
    initializeSubsystems();
    startAutoChooser();
  }

  private void configureButtonBindings() {
    // Gamepad 1 buttons


    // Gamepad 2 buttons

  }

  public void startAutoChooser() { //Note that I'm still working on this. It does not work yet.
    AutoMode selectedMode = new AutoChooser(this, gamepad1, telemetry).getSelectedMode();
    scheduleAutoCommands(selectedMode);
  }

  public void scheduleAutoCommands(AutoMode selectedAutoMode) { //Note that I'm still working on this. It does not work yet.
    new WaitUntilCommand(JavaBot::isStarted);
    if (selectedAutoMode
        == AutoMode.ExampleAuto) { // The switches we need here are not supported
      // till Java 14 :(.
      CommandScheduler.getInstance()
          .schedule(new InstantCommand());
    } else {
      telemetry.addLine("No auto was selected! There was likely an error.");
    }
  }

  public void run() {
    // telemetry
    telemetry.update();

    if (currentGameMode == gameMode.TeleOp) {
      gamepad1.readButtons();
      gamepad2.readButtons();
    }
  }
}
