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

public class bluePotato extends SequentialCommandGroup {

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

  public bluePotato(final Drivetrain drive, HardwareMap hw, Telemetry telemetry)
      throws IOException {
    this.follower = drive.getFollower();
    this.progressTracker = new ProgressTracker(follower, telemetry);

    PedroPathReader pp = new PedroPathReader("bluePotato.pp", hw.appContext);

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
              progressTracker.registerEvent("ShootCenter", 0.000);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, startPointTOshoot),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("ShootCenter");
                    }))),
        new ParallelRaceGroup(
            new WaitCommand(3000),
            new SequentialCommandGroup(
                new WaitCommand(1020),
                new InstantCommand(() -> progressTracker.executeEvent("PinballOpen")),
                new WaitCommand(1980))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootTOintakeTop);
              progressTracker.setCurrentPathName("shootTOintakeTop");
              progressTracker.registerEvent("IntakeOn", 0.950);
              progressTracker.registerEvent("PinballClose", 0.970);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, shootTOintakeTop),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOn")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOn");
                    }),
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("PinballClose")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("PinballClose");
                    }))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(intakeTopTOintakeTopDone);
              progressTracker.setCurrentPathName("intakeTopTOintakeTopDone");
            }),
        new FollowPathCommand(follower, intakeTopTOintakeTopDone),
        new WaitCommand(500),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(intakeTopDoneTOshoot);
              progressTracker.setCurrentPathName("intakeTopDoneTOshoot");
              progressTracker.registerEvent("IntakeOff", 0.090);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, intakeTopDoneTOshoot),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOff")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOff");
                    }))),
        new ParallelRaceGroup(
            new WaitCommand(3000),
            new SequentialCommandGroup(
                new WaitCommand(840),
                new InstantCommand(() -> progressTracker.executeEvent("PinballOpen")),
                new WaitCommand(2160))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootTOintakeMiddle);
              progressTracker.setCurrentPathName("shootTOintakeMiddle");
            }),
        new FollowPathCommand(follower, shootTOintakeMiddle),
        new ParallelRaceGroup(
            new WaitCommand(500),
            new SequentialCommandGroup(
                new WaitCommand(30),
                new InstantCommand(() -> progressTracker.executeEvent("IntakeOn")),
                new WaitCommand(20),
                new InstantCommand(() -> progressTracker.executeEvent("PinballClose")),
                new WaitCommand(450))),
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
              progressTracker.registerEvent("IntakeOff", 0.090);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, intakeMiddleDoneTOshoot),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOff")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOff");
                    }))),
        new ParallelRaceGroup(
            new WaitCommand(3000),
            new SequentialCommandGroup(
                new WaitCommand(180),
                new InstantCommand(() -> progressTracker.executeEvent("PinballOpen")),
                new WaitCommand(2820))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootTOintakeBottom);
              progressTracker.setCurrentPathName("shootTOintakeBottom");
              progressTracker.registerEvent("PinballClose", 0.310);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, shootTOintakeBottom),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("PinballClose")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("PinballClose");
                    }))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(intakeBottomTOintakeBottomDone);
              progressTracker.setCurrentPathName("intakeBottomTOintakeBottomDone");
              progressTracker.registerEvent("IntakeOn", 0.030);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, intakeBottomTOintakeBottomDone),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOn")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOn");
                    }))),
        new WaitCommand(500),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(intakeBottomDoneTOshoot);
              progressTracker.setCurrentPathName("intakeBottomDoneTOshoot");
              progressTracker.registerEvent("IntakeOff", 0.090);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, intakeBottomDoneTOshoot),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOff")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOff");
                    }))),
        new ParallelRaceGroup(
            new WaitCommand(3000),
            new SequentialCommandGroup(
                new WaitCommand(210),
                new InstantCommand(() -> progressTracker.executeEvent("PinballOpen")),
                new WaitCommand(2790))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootTOgate);
              progressTracker.setCurrentPathName("shootTOgate");
              progressTracker.registerEvent("PinballClose", 0.670);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, shootTOgate),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("PinballClose")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("PinballClose");
                    }))));
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
