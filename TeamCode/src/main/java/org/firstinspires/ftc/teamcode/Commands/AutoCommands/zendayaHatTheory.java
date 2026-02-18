/* ============================================================= *
 *        Pedro Pathing Plus Visualizer — Auto-Generated         *
 *                                                               *
 *  Version: 1.7.3.                                              *
 *  Copyright (c) 2026 Matthew Allen                             *
 *                                                               *
 *  THIS FILE IS AUTO-GENERATED — DO NOT EDIT MANUALLY.          *
 *  Changes will be overwritten when regenerated.                *
 * ============================================================= */

package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathingplus.pathing.ProgressTracker;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import java.io.IOException;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake;

public class zendayaHatTheory extends SequentialCommandGroup {

  private final Follower follower;
  private final ProgressTracker progressTracker;

  private final Intake intake;
  private final Outtake outtake;

  // Poses
  private Pose startPoint;
  private Pose shootPreload;
  private Pose topDone;

  // Path chains
  private PathChain startPointTOshootPreload;
  private PathChain shootPreloadTOtopDone;

  public zendayaHatTheory(
      final Drivetrain drive,
      final Intake intake,
      final Outtake outtake,
      HardwareMap hw,
      Telemetry telemetry)
      throws IOException {
    this.follower = drive.getFollower();
    this.progressTracker = new ProgressTracker(follower, telemetry);
    this.intake = intake;

    // Load poses
    startPoint = new Pose(17.476, 120.694, Math.toRadians(140));
    shootPreload = new Pose(42.572, 97.458, Math.toRadians(165));
    topDone = new Pose(14.022, 85.458, Math.toRadians(180));

    follower.setStartingPose(startPoint);

    buildPaths();

    addCommands(
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(startPointTOshootPreload);
              progressTracker.setCurrentPathName("startPointTOshootPreload");
              progressTracker.registerEvent("ShootCenter", 0.000);
              progressTracker.registerEvent("IntakeOn", 0.0);
            }),
        outtake(),
        new WaitCommand(5000),
        intakeOut(),
        stopIntake(),
        new FollowPathCommand(follower, shootPreloadTOtopDone));
  }

  public ParallelRaceGroup intakeOut() {
    return new ParallelRaceGroup(
        new WaitCommand(3000), new IntakeCommand(intake).withTimeout(3000));
  }

  public InstantCommand stopIntake() {
    return new InstantCommand(() -> intake.stop(), intake);
  }

  public ParallelRaceGroup outtake() {
    return new ParallelRaceGroup(
        new FollowPathCommand(follower, startPointTOshootPreload),
        new SequentialCommandGroup(
            new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
            new InstantCommand(
                () -> {
                  progressTracker.executeEvent("ShootCenter");
                })));
  }

  public InstantCommand stopOuttake() {
    return new InstantCommand(() -> outtake.stop(), outtake);
  }

  public void buildPaths() {
    startPointTOshootPreload =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPoint, shootPreload))
            .setTangentHeadingInterpolation()
            .setReversed()
            .build();

    shootPreloadTOtopDone =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shootPreload, topDone))
            .setTangentHeadingInterpolation()
            .build();
  }
}
