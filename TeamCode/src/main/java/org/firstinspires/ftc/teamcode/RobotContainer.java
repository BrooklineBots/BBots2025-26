package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoChooser;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.PedroAutoTest;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.OuttakeCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake;

public class RobotContainer {
  // Subsystems
  private Drivetrain drive;
  private Intake intake;
  private Outtake outtake;

  // private Storage storage;

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
    DoNothingAuto,
    ExampleAuto,
    PedroAutoTest;
  }

  private AutoMode currentAuto;

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
    drive = new Drivetrain(hardwareMap);
    outtake = new Outtake(hardwareMap, telemetry);
    // storage = new Storage(hardwareMap);
    // Register subsystems with scheduler
    CommandScheduler.getInstance().registerSubsystem(outtake, drive, intake);
  }

  public void configureTeleOp() {
    currentGameMode = gameMode.TeleOp;
    initializeSubsystems();

    // Default commands
    drive.setDefaultCommand(new DriveCommand(drive, gamepad1));
    // intake.setDefaultCommand(new IntakeCommand(intake));
    //    outtake.setDefaultCommand(new OuttakeCommand(outtake));
    // storage.setDefaultCommand(new StoreArtifactsCommand(storage));
    // Button bindings
    configureButtonBindings();
  }

  public void configureAuto() { // Note that I'm still working on this. It does not work yet.
    currentGameMode = gameMode.Auto;
    initializeSubsystems();

    // Schedule Auto Chooser
    CommandScheduler.getInstance().schedule(new AutoChooser(this, gamepad1, telemetry));
  }

  private void configureButtonBindings() {
    // Gamepad 1 buttons
    //    new GamepadButton(gamepad1, GamepadKeys.Button.A)
    //        .whenActive(new TransferToStorageCommand(intake, storage));
    new GamepadButton(gamepad1, GamepadKeys.Button.A).whenActive(new IntakeCommand(intake));
    new GamepadButton(gamepad1, GamepadKeys.Button.B)
        .whenActive(new InstantCommand(() -> intake.stop(), intake));
    //    new GamepadButton(gamepad1, GamepadKeys.Button.DPAD_DOWN)
    //        .whenActive(new InstantCommand(() -> storage.stop(), storage));
    //    new GamepadButton(gamepad1, GamepadKeys.Button.DPAD_UP)
    //        .whenActive(new StoreArtifactsCommand(storage));
    new GamepadButton(gamepad1, GamepadKeys.Button.X).whenActive(new OuttakeCommand(outtake));
    new GamepadButton(gamepad1, GamepadKeys.Button.Y)
        .whenActive(new InstantCommand(() -> outtake.stop(), outtake));

    // Gamepad 2 buttons

  }

  public void scheduleAutoCommands(final AutoMode selectedAutoMode) {
    telemetry.addData("Starting Auto Mode", selectedAutoMode);
    telemetry.update();

    if (selectedAutoMode == AutoMode.ExampleAuto) {
      CommandScheduler.getInstance().schedule(new InstantCommand(() -> outtake.shoot()));
    } else if (selectedAutoMode == AutoMode.DoNothingAuto) {
      CommandScheduler.getInstance().schedule(new InstantCommand());
    } else if (selectedAutoMode == AutoMode.PedroAutoTest) {
      CommandScheduler.getInstance().schedule(new PedroAutoTest(drive));
//      CommandScheduler.getInstance().schedule(new InstantCommand());

    } else {
      telemetry.addLine("No auto was selected! There was likely an error.");
      telemetry.update();
    }
  }

  public void run() {
    // telemetry
    // telemetry.addData("Currently shooting", outtake.getPower());
    telemetry.update();

    if (currentGameMode == gameMode.TeleOp) {
      gamepad1.readButtons();
      gamepad2.readButtons();
    }
    //    if (currentGameMode == gameMode.Auto) {
    //      dashboardPoseTracker.update();
    //      Drawing.drawPoseHistory(dashboardPoseTracker, "#4CAF50");
    //      Drawing.drawRobot(robot.follower.getPose(), "#4CAF50");
    //      Drawing.sendPacket();
    //
    //      // DO NOT REMOVE! Removing this will return stale data since bulk caching is on Manual
    // mode
    //      // Also only clearing the control hub to decrease loop times
    //      // This means if we start reading both hubs (which we aren't) we need to clear both
    //      robot.ControlHub.clearBulkCache();
    //    }
  }
}
