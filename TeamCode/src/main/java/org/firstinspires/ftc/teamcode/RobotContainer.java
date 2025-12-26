package org.firstinspires.ftc.teamcode;

import com.pedropathingplus.pathing.NamedCommands;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.button.GamepadButton;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;
import java.io.IOException;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.AutoChooser;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.BlueLeaveBigTri;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.BlueLeaveLittleTri;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.BlueTwelveArtifact;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.BlueTwelveArtifactFromObelisk;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.RedLeaveBigTri;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.RedLeaveLittleTri;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.RedTwelveArtifact;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.RedTwelveArtifactFromObelisk;
import org.firstinspires.ftc.teamcode.Commands.AutoCommands.reg;
import org.firstinspires.ftc.teamcode.Commands.BombshellPushUpCommand;
import org.firstinspires.ftc.teamcode.Commands.BombshellReverseCommand;
import org.firstinspires.ftc.teamcode.Commands.DriveCommand;
import org.firstinspires.ftc.teamcode.Commands.ExpelIntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.ExpelStorageAndIntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.OuttakeCommand;
import org.firstinspires.ftc.teamcode.Commands.OuttakeFastCommand;
import org.firstinspires.ftc.teamcode.Commands.StoreArtifactsCommand;
import org.firstinspires.ftc.teamcode.Commands.TransferToStorageCommand;
import org.firstinspires.ftc.teamcode.Subsystems.BombshellServo;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake;
import org.firstinspires.ftc.teamcode.Subsystems.Storage;

public class RobotContainer {
  // Subsystems
  private Drivetrain drive;
  private Drivetrain autoDrive;
  private Intake intake;
  private Outtake outtake;
  private BombshellServo bombshellServo;

  private Storage storage;

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
    reg,
    BlueTwelveArtifact,
    RedTwelveArtifact,
    BlueTwelveArtifactFromObelisk,
    RedTwelveArtifactFromObelisk,
    BlueLeaveLittleTri,
    RedLeaveLittleTri,
    BlueLeaveBigTri,
    RedLeaveBigTri;
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
    drive = new Drivetrain(hardwareMap, telemetry, currentGameMode);
    autoDrive = new Drivetrain(hardwareMap, telemetry, currentGameMode);
    outtake = new Outtake(hardwareMap, telemetry);
    storage = new Storage(hardwareMap);
    bombshellServo = new BombshellServo(hardwareMap, telemetry);
    // Register subsystems with scheduler
    CommandScheduler.getInstance()
        .registerSubsystem(drive, autoDrive, intake, storage, outtake, bombshellServo);
  }

  public void configureTeleOp() {
    currentGameMode = gameMode.TeleOp;
    initializeSubsystems();

    // Default commands
    drive.setDefaultCommand(new DriveCommand(drive, gamepad1));
    // Button bindings
    configureButtonBindings();
  }

  public void configureAuto() { // Note that I'm still working on this. It does not work yet.
    currentGameMode = gameMode.Auto;
    initializeSubsystems();
    registerNamedCommands();

    // Schedule Auto Chooser
    CommandScheduler.getInstance().schedule(new AutoChooser(this, gamepad1, telemetry));
  }

  private void configureButtonBindings() {
    // Gamepad 1 buttons
    new GamepadButton(gamepad1, GamepadKeys.Button.DPAD_UP)
        .whenHeld(new TransferToStorageCommand(intake, storage));
    new GamepadButton(gamepad1, GamepadKeys.Button.B)
        .whenActive(new InstantCommand(() -> intake.stop(), intake));
    new GamepadButton(gamepad1, GamepadKeys.Button.B)
        .whenActive(new InstantCommand(() -> storage.stop(), storage));
    new GamepadButton(gamepad1, GamepadKeys.Button.DPAD_DOWN)
        .whenHeld(new ExpelStorageAndIntakeCommand(intake, storage));
    new GamepadButton(gamepad1, GamepadKeys.Button.DPAD_RIGHT)
        .whenHeld(new StoreArtifactsCommand(storage));
    new GamepadButton(gamepad1, GamepadKeys.Button.A).whenHeld(new IntakeCommand(intake));
    new GamepadButton(gamepad1, GamepadKeys.Button.Y).whenHeld(new ExpelIntakeCommand(intake));

    new GamepadButton(gamepad2, GamepadKeys.Button.X).whenActive(new OuttakeCommand(outtake));
    new GamepadButton(gamepad2, GamepadKeys.Button.Y)
        .whenActive(new InstantCommand(() -> outtake.stop(), outtake));
    new GamepadButton(gamepad2, GamepadKeys.Button.A).whenActive(new OuttakeFastCommand(outtake));
    new GamepadButton(gamepad2, GamepadKeys.Button.RIGHT_BUMPER)
        .whenHeld(new BombshellPushUpCommand(bombshellServo));
    new GamepadButton(gamepad2, GamepadKeys.Button.LEFT_BUMPER)
        .whenHeld(new BombshellReverseCommand(bombshellServo));
    //    new GamepadButton(gamepad1, GamepadKeys.Button.DPAD_UP)
    //        .whenPressed(
    //            new SequentialCommandGroup(
    //                new InstantCommand(() -> drive.setSpeeds(1, 0, 0, 0)),
    //                new WaitCommand(2000),
    //                new InstantCommand(() -> drive.setSpeeds(0, 0, 0, 0))));
    //    new GamepadButton(gamepad1, GamepadKeys.Button.DPAD_DOWN)
    //        .whenPressed(
    //            new SequentialCommandGroup(
    //                new InstantCommand(() -> drive.setSpeeds(0, 1, 0, 0)),
    //                new WaitCommand(2000),
    //                new InstantCommand(() -> drive.setSpeeds(0, 0, 0, 0))));
    //    new GamepadButton(gamepad1, GamepadKeys.Button.DPAD_RIGHT)
    //        .whenPressed(
    //            new SequentialCommandGroup(
    //                new InstantCommand(() -> drive.setSpeeds(0, 0, 1, 0)),
    //                new WaitCommand(2000),
    //                new InstantCommand(() -> drive.setSpeeds(0, 0, 0, 0))));
    //    new GamepadButton(gamepad1, GamepadKeys.Button.DPAD_LEFT)
    //        .whenPressed(
    //            new SequentialCommandGroup(
    //                new InstantCommand(() -> drive.setSpeeds(0, 0, 0, 1)),
    //                new WaitCommand(2000),
    //                new InstantCommand(() -> drive.setSpeeds(0, 0, 0, 0))));

    // Gamepad 2 buttons

  }

  public void scheduleAutoCommands(final AutoMode selectedAutoMode) {
    telemetry.addData("Starting Auto Mode", selectedAutoMode);
    telemetry.update();

    try {
      if (selectedAutoMode == AutoMode.DoNothingAuto) {
        CommandScheduler.getInstance().schedule(new InstantCommand());
      } else if (selectedAutoMode == AutoMode.reg) {
        CommandScheduler.getInstance().schedule(new reg(autoDrive, hardwareMap));
      } else if (selectedAutoMode == AutoMode.BlueTwelveArtifact) {
        CommandScheduler.getInstance()
            .schedule(new BlueTwelveArtifact(autoDrive, intake, storage, outtake));
      } else if (selectedAutoMode == AutoMode.RedTwelveArtifact) {
        CommandScheduler.getInstance().schedule(new RedTwelveArtifact(autoDrive, intake));
      } else if (selectedAutoMode == AutoMode.BlueTwelveArtifactFromObelisk) {
        CommandScheduler.getInstance()
            .schedule(new BlueTwelveArtifactFromObelisk(autoDrive, intake));
      } else if (selectedAutoMode == AutoMode.RedTwelveArtifactFromObelisk) {
        CommandScheduler.getInstance()
            .schedule(new RedTwelveArtifactFromObelisk(autoDrive, intake));
      } else if (selectedAutoMode == AutoMode.BlueLeaveLittleTri) {
        CommandScheduler.getInstance().schedule(new BlueLeaveLittleTri(autoDrive));
      } else if (selectedAutoMode == AutoMode.RedLeaveLittleTri) {
        CommandScheduler.getInstance().schedule(new RedLeaveLittleTri(autoDrive));
      } else if (selectedAutoMode == AutoMode.BlueLeaveBigTri) {
        CommandScheduler.getInstance().schedule(new BlueLeaveBigTri(autoDrive));
      } else if (selectedAutoMode == AutoMode.RedLeaveBigTri) {
        CommandScheduler.getInstance().schedule(new RedLeaveBigTri(autoDrive));
      } else {
        telemetry.addLine("No auto was selected! There was likely an error.");
        telemetry.update();
      }
    } catch (final IOException error) {
      telemetry.addLine("A critical IOException error has occurred. Doing nothing. ");
      telemetry.update();
      CommandScheduler.getInstance().schedule(new InstantCommand());
    }
  }

  private void registerNamedCommands() {

    // Register  commands
    NamedCommands.registerCommand(
        "IntakeOn", new InstantCommand(() -> intake.intake()), "Turn intake on (intake mode)");

    NamedCommands.registerCommand(
        "IntakeOff", new InstantCommand(() -> intake.stop()), "Turn intake off");
    NamedCommands.registerCommand(
        "ShootCenter",
        new SequentialCommandGroup(new OuttakeFastCommand(outtake), new WaitCommand(3000)),
        "Outtakes artifacts");

    NamedCommands.listAllCommands(telemetry);
  }

  public void run() {
    // telemetry`
    // telemetry.addData("Currently shooting", outtake.getPower());
    //    telemetry.update();

    if (currentGameMode == gameMode.TeleOp) {
      gamepad1.readButtons();
      gamepad2.readButtons();
    }
    if (currentGameMode == gameMode.Auto) {
      //      telemetry.addData("Pos x", autoDrive.getFollower().getPose().getX());
      //      telemetry.addData("Pos y", autoDrive.getFollower().getPose().getY());
      //      telemetry.addData("Heading: ", autoDrive.getFollower().getPose().getHeading());
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
