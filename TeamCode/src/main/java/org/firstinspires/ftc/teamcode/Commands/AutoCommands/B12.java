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
import com.pedropathing.geometry.BezierCurve;
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

public class B12 extends SequentialCommandGroup {

  private final Follower follower;
  private final ProgressTracker progressTracker;

  private final Outtake outtake;
  private final Intake intake;

  // Poses
  private Pose startPoint;
  private Pose shootPreload;
  private Pose topStart;
  private Pose topDone;
  private Pose shootTop;
  private Pose middleBegin;
  private Pose middleDone;
  private Pose shootMiddle;
  private Pose shootMiddle_line6_control1;
  private Pose bottomBegin;
  private Pose bottomEnd;
  private Pose shootBottom;
  private Pose toGate;

  // Path chains
  private PathChain startPointTOshootPreload;
  private PathChain shootPreloadTOtopStart;
  private PathChain topStartTOtopDone;
  private PathChain topDoneTOshootTop;
  private PathChain shootTopTOmiddleBegin;
  private PathChain middleBeginTOmiddleDone;
  private PathChain middleDoneTOshootMiddle;
  private PathChain shootMiddleTObottomBegin;
  private PathChain bottomBeginTObottomEnd;
  private PathChain bottomEndTOshootBottom;
  private PathChain shootBottomTOtoGate;

  public B12(
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
    topDone = new Pose(10.414, 85.000, Math.toRadians(180));
    shootTop = new Pose(46.000, 97.000, Math.toRadians(145));
    middleBegin = new Pose(44.000, 60.306, Math.toRadians(180));
    middleDone = new Pose(10.414, 60.306, Math.toRadians(180));
    shootMiddle = new Pose(46.000, 97.000, Math.toRadians(145));
    shootMiddle_line6_control1 = new Pose(45.000, 69.000);
    bottomBegin = new Pose(44.000, 36.175, Math.toRadians(180));
    bottomEnd = new Pose(10.414, 36.175, Math.toRadians(180));
    shootBottom = new Pose(46.000, 97.000, Math.toRadians(145));
    toGate = new Pose(19.690, 65.424, Math.toRadians(180));

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
        new FollowPathCommand(follower, shootPreloadTOtopStart),
        new WaitCommand(500),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(topStartTOtopDone);
              progressTracker.setCurrentPathName("topStartTOtopDone");
            }),
        intakeWhileRunning(topStartTOtopDone),
        stopIntake(),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(topDoneTOshootTop);
              progressTracker.setCurrentPathName("topDoneTOshootTop");
            }),
        new FollowPathCommand(follower, topDoneTOshootTop),
        intakeOut(),
        stopIntake(),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootTopTOmiddleBegin);
              progressTracker.setCurrentPathName("shootTopTOmiddleBegin");
            }),
        new FollowPathCommand(follower, shootTopTOmiddleBegin),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(middleBeginTOmiddleDone);
              progressTracker.setCurrentPathName("middleBeginTOmiddleDone");
            }),
        intakeWhileRunning(middleBeginTOmiddleDone),
        stopIntake(),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(middleDoneTOshootMiddle);
              progressTracker.setCurrentPathName("middleDoneTOshootMiddle");
            }),
        new FollowPathCommand(follower, middleDoneTOshootMiddle),
        intakeOut(),
        stopIntake(),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootMiddleTObottomBegin);
              progressTracker.setCurrentPathName("shootMiddleTObottomBegin");
            }),
        new FollowPathCommand(follower, shootMiddleTObottomBegin),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(bottomBeginTObottomEnd);
              progressTracker.setCurrentPathName("bottomBeginTObottomEnd");
            }),
        intakeWhileRunning(bottomBeginTObottomEnd),
        stopIntake(),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(bottomEndTOshootBottom);
              progressTracker.setCurrentPathName("bottomEndTOshootBottom");
            }),
        new FollowPathCommand(follower, bottomEndTOshootBottom),
        intakeOut(),
        stopIntake(),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootBottomTOtoGate);
              progressTracker.setCurrentPathName("shootBottomTOtoGate");
            }),
        new FollowPathCommand(follower, shootBottomTOtoGate));
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

    topStartTOtopDone =
        follower
            .pathBuilder()
            .addPath(new BezierLine(topStart, topDone))
            .setConstantHeadingInterpolation(Math.toRadians(180))
            .build();

    topDoneTOshootTop =
        follower
            .pathBuilder()
            .addPath(new BezierLine(topDone, shootTop))
            .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(145))
            .build();

    shootTopTOmiddleBegin =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shootTop, middleBegin))
            .setLinearHeadingInterpolation(Math.toRadians(150), Math.toRadians(180))
            .build();

    middleBeginTOmiddleDone =
        follower
            .pathBuilder()
            .addPath(new BezierLine(middleBegin, middleDone))
            .setConstantHeadingInterpolation(Math.toRadians(180))
            .build();

    middleDoneTOshootMiddle =
        follower
            .pathBuilder()
            .addPath(new BezierCurve(middleDone, shootMiddle_line6_control1, shootMiddle))
            .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(145))
            .build();

    shootMiddleTObottomBegin =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shootMiddle, bottomBegin))
            .setLinearHeadingInterpolation(Math.toRadians(150), Math.toRadians(180))
            .build();

    bottomBeginTObottomEnd =
        follower
            .pathBuilder()
            .addPath(new BezierLine(bottomBegin, bottomEnd))
            .setConstantHeadingInterpolation(Math.toRadians(180))
            .build();

    bottomEndTOshootBottom =
        follower
            .pathBuilder()
            .addPath(new BezierLine(bottomEnd, shootBottom))
            .setLinearHeadingInterpolation(Math.toRadians(180), Math.toRadians(145))
            .build();

    shootBottomTOtoGate =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shootBottom, toGate))
            .setLinearHeadingInterpolation(Math.toRadians(150), Math.toRadians(180))
            .build();
  }
}
