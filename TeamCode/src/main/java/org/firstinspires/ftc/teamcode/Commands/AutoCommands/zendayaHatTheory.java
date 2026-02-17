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
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class zendayaHatTheory extends SequentialCommandGroup {

  private final Follower follower;
  private final ProgressTracker progressTracker;

  // Poses
  private Pose startPoint;
  private Pose shootPreload;
  private Pose topDone;

  // Path chains
  private PathChain startPointTOshootPreload;
  private PathChain shootPreloadTOtopDone;

  public zendayaHatTheory(final Drivetrain drive, HardwareMap hw, Telemetry telemetry)
      throws IOException {
    this.follower = drive.getFollower();
    this.progressTracker = new ProgressTracker(follower, telemetry);

    // Load poses
    startPoint = new Pose(17.476, 120.694, Math.toRadians(140));
    shootPreload = new Pose(51.572, 92.458, Math.toRadians(140));
    topDone = new Pose(14.022, 92.458, Math.toRadians(180));

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
        new ParallelRaceGroup(
            new FollowPathCommand(follower, startPointTOshootPreload),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("ShootCenter");
                    }))),
        new ParallelCommandGroup(
            new WaitCommand(6000),
            new InstantCommand(() -> progressTracker.shouldTriggerEvent("IntakeOn"))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootPreloadTOtopDone);
              progressTracker.setCurrentPathName("shootPreloadTOtopDone");
              progressTracker.registerEvent("IntakeOff", 0.090);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, shootPreloadTOtopDone),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOff")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOff");
                    }))),
        new WaitCommand(500));
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
