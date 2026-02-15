/* ============================================================= *
 *        Pedro Pathing Plus Visualizer — Auto-Generated         *
 *                                                               *
 *  Version: 1.7.2.                                              *
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
import com.pedropathingplus.PedroPathReader;
import com.pedropathingplus.pathing.ProgressTracker;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
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
  private Pose intakeTop;
  private Pose topDone;
  private Pose shootTop;

  // Path chains
  private PathChain startPointTOshootPreload;
  private PathChain shootPreloadTOintakeTop;
  private PathChain intakeTopTOtopDone;
  private PathChain topDoneTOshootTop;

  public zendayaHatTheory(final Drivetrain drive, HardwareMap hw, Telemetry telemetry)
      throws IOException {
    this.follower = drive.getFollower();
    this.progressTracker = new ProgressTracker(follower, telemetry);

    PedroPathReader pp = new PedroPathReader("zendayaHatTheory.pp", hw.appContext);

    // Load poses
    startPoint = pp.get("startPoint");
    shootPreload = pp.get("shootPreload");
    intakeTop = pp.get("intakeTop");
    topDone = pp.get("topDone");
    shootTop = pp.get("shootTop");

    follower.setStartingPose(startPoint);

    buildPaths();

    addCommands(
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(startPointTOshootPreload);
              progressTracker.setCurrentPathName("startPointTOshootPreload");
            }),
        new FollowPathCommand(follower, startPointTOshootPreload),
        new WaitCommand(3000),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootPreloadTOintakeTop);
              progressTracker.setCurrentPathName("shootPreloadTOintakeTop");
            }),
        new FollowPathCommand(follower, shootPreloadTOintakeTop),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(intakeTopTOtopDone);
              progressTracker.setCurrentPathName("intakeTopTOtopDone");
            }),
        new FollowPathCommand(follower, intakeTopTOtopDone),
        new WaitCommand(500),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(topDoneTOshootTop);
              progressTracker.setCurrentPathName("topDoneTOshootTop");
            }),
        new FollowPathCommand(follower, topDoneTOshootTop));
  }

  public void buildPaths() {
    startPointTOshootPreload =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPoint, shootPreload))
            .setLinearHeadingInterpolation(startPoint.getHeading(), shootPreload.getHeading())
            .build();

    shootPreloadTOintakeTop =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shootPreload, intakeTop))
            .setLinearHeadingInterpolation(shootPreload.getHeading(), intakeTop.getHeading())
            .build();

    intakeTopTOtopDone =
        follower
            .pathBuilder()
            .addPath(new BezierLine(intakeTop, topDone))
            .setTangentHeadingInterpolation()
            .build();

    topDoneTOshootTop =
        follower
            .pathBuilder()
            .addPath(new BezierLine(topDone, shootTop))
            .setLinearHeadingInterpolation(topDone.getHeading(), shootTop.getHeading())
            .build();
  }
}
