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
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import java.io.IOException;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class elsa extends SequentialCommandGroup {

  private final Follower follower;
  private final ProgressTracker progressTracker;

  // Poses
  private Pose startPoint;
  private Pose shoot;
  private Pose intakeTop;
  private Pose intakeTopDone;
  private Pose intakeMiddle;
  private Pose intakeMiddleDone;
  private Pose intakeBottom;
  private Pose intakeBottomDone;
  private Pose gate;

  // Path chains
  private PathChain startPointTOshoot;
  private PathChain shootTOintakeTop;
  private PathChain intakeTopTOintakeTopDone;
  private PathChain intakeTopDoneTOshoot;
  private PathChain shootTOintakeMiddle;
  private PathChain intakeMiddleTOintakeMiddleDone;
  private PathChain intakeMiddleDoneTOshoot;
  private PathChain shootTOintakeBottom;
  private PathChain intakeBottomTOintakeBottomDone;
  private PathChain intakeBottomDoneTOshoot;
  private PathChain shootTOgate;

  public elsa(final Drivetrain drive, HardwareMap hw, Telemetry telemetry) throws IOException {
    this.follower = drive.getFollower();
    this.progressTracker = new ProgressTracker(follower, telemetry);

    PedroPathReader pp = new PedroPathReader("elsa", hw.appContext);

    // Load poses
    startPoint = pp.get("startPoint");
    shoot = pp.get("shoot");
    intakeTop = pp.get("intakeTop");
    intakeTopDone = pp.get("intakeTopDone");
    intakeMiddle = pp.get("intakeMiddle");
    intakeMiddleDone = pp.get("intakeMiddleDone");
    intakeBottom = pp.get("intakeBottom");
    intakeBottomDone = pp.get("intakeBottomDone");
    gate = pp.get("gate");

    follower.setStartingPose(startPoint);

    buildPaths();

    addCommands(
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(startPointTOshoot);
              progressTracker.setCurrentPathName("startPointTOshoot");
            }),
        new FollowPathCommand(follower, startPointTOshoot),
        new WaitCommand(3000),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootTOintakeTop);
              progressTracker.setCurrentPathName("shootTOintakeTop");
            }),
        new FollowPathCommand(follower, shootTOintakeTop),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(intakeTopTOintakeTopDone);
              progressTracker.setCurrentPathName("intakeTopTOintakeTopDone");
              progressTracker.registerEvent("IntakeOn", 0.020);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, intakeTopTOintakeTopDone),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOn")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOn");
                    }))),
        new WaitCommand(500),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(intakeTopDoneTOshoot);
              progressTracker.setCurrentPathName("intakeTopDoneTOshoot");
              progressTracker.registerEvent("", 0.070);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, intakeTopDoneTOshoot),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("");
                    }))),
        new WaitCommand(3000),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootTOintakeMiddle);
              progressTracker.setCurrentPathName("shootTOintakeMiddle");
            }),
        new FollowPathCommand(follower, shootTOintakeMiddle),
        new WaitCommand(500),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(intakeMiddleTOintakeMiddleDone);
              progressTracker.setCurrentPathName("intakeMiddleTOintakeMiddleDone");
            }),
        new FollowPathCommand(follower, intakeMiddleTOintakeMiddleDone),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(intakeMiddleDoneTOshoot);
              progressTracker.setCurrentPathName("intakeMiddleDoneTOshoot");
            }),
        new FollowPathCommand(follower, intakeMiddleDoneTOshoot),
        new WaitCommand(3000),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootTOintakeBottom);
              progressTracker.setCurrentPathName("shootTOintakeBottom");
            }),
        new FollowPathCommand(follower, shootTOintakeBottom),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(intakeBottomTOintakeBottomDone);
              progressTracker.setCurrentPathName("intakeBottomTOintakeBottomDone");
            }),
        new FollowPathCommand(follower, intakeBottomTOintakeBottomDone),
        new WaitCommand(500),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(intakeBottomDoneTOshoot);
              progressTracker.setCurrentPathName("intakeBottomDoneTOshoot");
            }),
        new FollowPathCommand(follower, intakeBottomDoneTOshoot),
        new WaitCommand(3000),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootTOgate);
              progressTracker.setCurrentPathName("shootTOgate");
            }),
        new FollowPathCommand(follower, shootTOgate));
  }

  public void buildPaths() {
    startPointTOshoot =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPoint, shoot))
            .setLinearHeadingInterpolation(startPoint.getHeading(), shoot.getHeading())
            .build();

    shootTOintakeTop =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shoot, intakeTop))
            .setLinearHeadingInterpolation(shoot.getHeading(), intakeTop.getHeading())
            .build();

    intakeTopTOintakeTopDone =
        follower
            .pathBuilder()
            .addPath(new BezierLine(intakeTop, intakeTopDone))
            .setLinearHeadingInterpolation(intakeTop.getHeading(), intakeTopDone.getHeading())
            .build();

    intakeTopDoneTOshoot =
        follower
            .pathBuilder()
            .addPath(new BezierLine(intakeTopDone, shoot))
            .setLinearHeadingInterpolation(intakeTopDone.getHeading(), shoot.getHeading())
            .build();

    shootTOintakeMiddle =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shoot, intakeMiddle))
            .setLinearHeadingInterpolation(shoot.getHeading(), intakeMiddle.getHeading())
            .build();

    intakeMiddleTOintakeMiddleDone =
        follower
            .pathBuilder()
            .addPath(new BezierLine(intakeMiddle, intakeMiddleDone))
            .setLinearHeadingInterpolation(intakeMiddle.getHeading(), intakeMiddleDone.getHeading())
            .build();

    intakeMiddleDoneTOshoot =
        follower
            .pathBuilder()
            .addPath(new BezierLine(intakeMiddleDone, shoot))
            .setLinearHeadingInterpolation(intakeMiddleDone.getHeading(), shoot.getHeading())
            .build();

    shootTOintakeBottom =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shoot, intakeBottom))
            .setLinearHeadingInterpolation(shoot.getHeading(), intakeBottom.getHeading())
            .build();

    intakeBottomTOintakeBottomDone =
        follower
            .pathBuilder()
            .addPath(new BezierLine(intakeBottom, intakeBottomDone))
            .setLinearHeadingInterpolation(intakeBottom.getHeading(), intakeBottomDone.getHeading())
            .build();

    intakeBottomDoneTOshoot =
        follower
            .pathBuilder()
            .addPath(new BezierLine(intakeBottomDone, shoot))
            .setLinearHeadingInterpolation(intakeBottomDone.getHeading(), shoot.getHeading())
            .build();

    shootTOgate =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shoot, gate))
            .setLinearHeadingInterpolation(shoot.getHeading(), gate.getHeading())
            .build();
  }
}
