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
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import java.io.IOException;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.Outtake;

public class B3 extends SequentialCommandGroup {

  private final Follower follower;
  private final ProgressTracker progressTracker;

  private final Outtake outtake;
  private final Intake intake;

  // Poses
  private Pose startPoint;
  private Pose shootPreload;
  private Pose topStart;

  // Path chains
  private PathChain startPointTOshootPreload;
  private PathChain shootPreloadTOtopStart;

  public B3(
      final Drivetrain drive,
      final Outtake outtake,
      final Intake intake,
      HardwareMap hw,
      Telemetry telemetry)
      throws IOException {
    this.follower = drive.getFollower();
    this.progressTracker = new ProgressTracker(follower, telemetry);
    this.outtake = outtake;
    this.intake = intake;

    // Load poses
    startPoint = new Pose(17.476, 120.694, Math.toRadians(140));
    shootPreload = new Pose(44.000, 97.000, Math.toRadians(145));
    topStart = new Pose(44.000, 85.000, Math.toRadians(180));

    follower.setStartingPose(startPoint);

    buildPaths();

    addCommands(
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(startPointTOshootPreload);
              progressTracker.setCurrentPathName("startPointTOshootPreload");
              progressTracker.registerEvent("ShootCenter", 0.000);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, startPointTOshootPreload),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("ShootCenter");
                    }))),
        new WaitCommand(5000),
        intakeOut(),
        stopIntake(),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootPreloadTOtopStart);
              progressTracker.setCurrentPathName("shootPreloadTOtopStart");
            }),
        new FollowPathCommand(follower, shootPreloadTOtopStart));
  }

  public ParallelRaceGroup intakeOut() {
    return new ParallelRaceGroup(
        new WaitCommand(2000), new IntakeOutCommand(intake).withTimeout(2000));
  }

  private ParallelCommandGroup intakeWhileRunning(final PathChain path) {
    return new ParallelCommandGroup(
        new FollowPathCommand(follower, path), new IntakeCommand(intake).withTimeout(1500));
  }

  public InstantCommand stopIntake() {
    return new InstantCommand(() -> intake.stop(), intake);
  }

  public ParallelRaceGroup outtake(PathChain path) {
    return new ParallelRaceGroup(
        new FollowPathCommand(follower, path),
        new SequentialCommandGroup(
            new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
            new InstantCommand(
                () -> {
                  progressTracker.executeEvent("ShootCenter");
                })));
  }

  public void buildPaths() {
    startPointTOshootPreload =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPoint, shootPreload))
            .setLinearHeadingInterpolation(Math.toRadians(140), Math.toRadians(145))
            .build();

    shootPreloadTOtopStart =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shootPreload, topStart))
            .setLinearHeadingInterpolation(Math.toRadians(150), Math.toRadians(180))
            .build();
  }
}
