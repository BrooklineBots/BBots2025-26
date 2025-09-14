package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoChooser;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class RobotContainer {
  // Subsystems
  private Intake intake;

  // Dependencies
  private final HardwareMap hardwareMap;
  private final Telemetry telemetry;
  private final GamepadEx gamepad1;
  private final GamepadEx gamepad2;
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
      final HardwareMap hardwareMap,
      final Gamepad gamepad1,
      final Gamepad gamepad2,
      final Telemetry telemetry,
      final CommandOpMode JavaBot) {
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
    intake = new Intake(hardwareMap);
    // Register subsystems with scheduler
    CommandScheduler.getInstance().registerSubsystem(intake);
  }

  public void configureTeleOp() {
    currentGameMode = gameMode.TeleOp;
    initializeSubsystems();

    // Default commands
    intake.setDefaultCommand(new IntakeCommand(intake, gamepad1));
    // Button bindings
    configureButtonBindings();
  }

  public void configureAuto() { // Note that I'm still working on this. It does not work yet.
    currentGameMode = gameMode.Auto;
    initializeSubsystems();
    startAutoChooser();
  }

  private void configureButtonBindings() {
    // Gamepad 1 buttons
    new GamepadButton(gamepad1, GamepadKeys.Button.A)
        .whenActive(new IntakeCommand(intake, gamepad1));
    new GamepadButton(gamepad1, GamepadKeys.Button.B)
        .whenActive(new IntakeCommand(intake, gamepad1));
    // Gamepad 2 buttons

  }

  public void startAutoChooser() { // Note that I'm still working on this. It does not work yet.
    final AutoMode selectedMode = new AutoChooser(this, gamepad1, telemetry).getSelectedMode();
    scheduleAutoCommands(selectedMode);
  }

  public void scheduleAutoCommands(
      final AutoMode
          selectedAutoMode) { // Note that I'm still working on this. It does not work yet.
    new WaitUntilCommand(JavaBot::isStarted);
    if (selectedAutoMode == AutoMode.ExampleAuto) { // The switches we need here are not supported
      // till Java 14 :(.
      CommandScheduler.getInstance().schedule(new InstantCommand());
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
