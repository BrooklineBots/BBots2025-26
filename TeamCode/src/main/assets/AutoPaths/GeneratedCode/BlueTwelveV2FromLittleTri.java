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
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathingplus.PedroPathReader;
import com.pedropathingplus.pathing.ProgressTracker;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import java.io.IOException;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class BlueTwelveV2FromLittleTri extends SequentialCommandGroup {

  private final Follower follower;
  private final ProgressTracker progressTracker;

  // Poses
  private Pose startPoint;
  private Pose shoot;
  private Pose ToIntakeOne;
  private Pose IntakeOne;
  private Pose shoot_line3_control1;
  private Pose ToIntakeTwo;
  private Pose IntakeTwo;
  private Pose shoot_line6_control1;
  private Pose ToIntakeThree;
  private Pose IntakeThree;
  private Pose OuttakeThree;

  // Path chains
  private PathChain startPointTOshoot;
  private PathChain shootTOToIntakeOne;
  private PathChain ToIntakeOneTOIntakeOne;
  private PathChain IntakeOneTOshoot;
  private PathChain shootTOToIntakeTwo;
  private PathChain ToIntakeTwoTOIntakeTwo;
  private PathChain IntakeTwoTOshoot;
  private PathChain shootTOToIntakeThree;
  private PathChain ToIntakeThreeTOIntakeThree;
  private PathChain IntakeThreeTOOuttakeThree;

  public BlueTwelveV2FromLittleTri(final Drivetrain drive, HardwareMap hw, Telemetry telemetry)
      throws IOException {
    this.follower = drive.getFollower();
    this.progressTracker = new ProgressTracker(follower, telemetry);

    PedroPathReader pp = new PedroPathReader("BlueTwelveV2FromLittleTri", hw.appContext);

    // Load poses
    startPoint = pp.get("startPoint");
    shoot = pp.get("shoot");
    ToIntakeOne = pp.get("ToIntakeOne");
    IntakeOne = pp.get("IntakeOne");
    shoot_line3_control1 = pp.get("shoot_control1");
    ToIntakeTwo = pp.get("ToIntakeTwo");
    IntakeTwo = pp.get("IntakeTwo");
    shoot_line6_control1 = pp.get("shoot_control1");
    ToIntakeThree = pp.get("ToIntakeThree");
    IntakeThree = pp.get("IntakeThree");
    OuttakeThree = pp.get("OuttakeThree");

    follower.setStartingPose(startPoint);

    buildPaths();

    addCommands(
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(startPointTOshoot);
              progressTracker.setCurrentPathName("startPointTOshoot");
              progressTracker.registerEvent("ShootCenter", 1.000);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, startPointTOshoot),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("ShootCenter");
                    }))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootTOToIntakeOne);
              progressTracker.setCurrentPathName("shootTOToIntakeOne");
            }),
        new FollowPathCommand(follower, shootTOToIntakeOne),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(ToIntakeOneTOIntakeOne);
              progressTracker.setCurrentPathName("ToIntakeOneTOIntakeOne");
              progressTracker.registerEvent("IntakeOn", 0.000);
              progressTracker.registerEvent("IntakeOff", 1.000);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, ToIntakeOneTOIntakeOne),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOn")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOn");
                    }),
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOff")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOff");
                    }))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(IntakeOneTOshoot);
              progressTracker.setCurrentPathName("IntakeOneTOshoot");
              progressTracker.registerEvent("ShootCenter", 1.000);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, IntakeOneTOshoot),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("ShootCenter");
                    }))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootTOToIntakeTwo);
              progressTracker.setCurrentPathName("shootTOToIntakeTwo");
            }),
        new FollowPathCommand(follower, shootTOToIntakeTwo),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(ToIntakeTwoTOIntakeTwo);
              progressTracker.setCurrentPathName("ToIntakeTwoTOIntakeTwo");
              progressTracker.registerEvent("IntakeOn", 0.000);
              progressTracker.registerEvent("IntakeOff", 1.000);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, ToIntakeTwoTOIntakeTwo),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOn")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOn");
                    }),
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOff")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOff");
                    }))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(IntakeTwoTOshoot);
              progressTracker.setCurrentPathName("IntakeTwoTOshoot");
              progressTracker.registerEvent("ShootCenter", 1.000);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, IntakeTwoTOshoot),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("ShootCenter");
                    }))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(shootTOToIntakeThree);
              progressTracker.setCurrentPathName("shootTOToIntakeThree");
            }),
        new FollowPathCommand(follower, shootTOToIntakeThree),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(ToIntakeThreeTOIntakeThree);
              progressTracker.setCurrentPathName("ToIntakeThreeTOIntakeThree");
              progressTracker.registerEvent("IntakeOn", 0.000);
              progressTracker.registerEvent("IntakeOff", 1.000);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, ToIntakeThreeTOIntakeThree),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOn")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOn");
                    }),
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOff")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOff");
                    }))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(IntakeThreeTOOuttakeThree);
              progressTracker.setCurrentPathName("IntakeThreeTOOuttakeThree");
              progressTracker.registerEvent("ShootCenter", 1.000);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, IntakeThreeTOOuttakeThree),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("ShootCenter");
                    }))));
  }

  public void buildPaths() {
    startPointTOshoot =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPoint, shoot))
            .setLinearHeadingInterpolation(startPoint.getHeading(), shoot.getHeading())
            .build();

    shootTOToIntakeOne =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shoot, ToIntakeOne))
            .setLinearHeadingInterpolation(shoot.getHeading(), ToIntakeOne.getHeading())
            .build();

    ToIntakeOneTOIntakeOne =
        follower
            .pathBuilder()
            .addPath(new BezierLine(ToIntakeOne, IntakeOne))
            .setTangentHeadingInterpolation()
            .build();

    IntakeOneTOshoot =
        follower
            .pathBuilder()
            .addPath(new BezierCurve(IntakeOne, shoot_line3_control1, shoot))
            .setLinearHeadingInterpolation(IntakeOne.getHeading(), shoot.getHeading())
            .build();

    shootTOToIntakeTwo =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shoot, ToIntakeTwo))
            .setLinearHeadingInterpolation(shoot.getHeading(), ToIntakeTwo.getHeading())
            .build();

    ToIntakeTwoTOIntakeTwo =
        follower
            .pathBuilder()
            .addPath(new BezierLine(ToIntakeTwo, IntakeTwo))
            .setLinearHeadingInterpolation(ToIntakeTwo.getHeading(), IntakeTwo.getHeading())
            .build();

    IntakeTwoTOshoot =
        follower
            .pathBuilder()
            .addPath(new BezierCurve(IntakeTwo, shoot_line6_control1, shoot))
            .setLinearHeadingInterpolation(IntakeTwo.getHeading(), shoot.getHeading())
            .build();

    shootTOToIntakeThree =
        follower
            .pathBuilder()
            .addPath(new BezierLine(shoot, ToIntakeThree))
            .setLinearHeadingInterpolation(shoot.getHeading(), ToIntakeThree.getHeading())
            .build();

    ToIntakeThreeTOIntakeThree =
        follower
            .pathBuilder()
            .addPath(new BezierLine(ToIntakeThree, IntakeThree))
            .setLinearHeadingInterpolation(ToIntakeThree.getHeading(), IntakeThree.getHeading())
            .build();

    IntakeThreeTOOuttakeThree =
        follower
            .pathBuilder()
            .addPath(new BezierLine(IntakeThree, OuttakeThree))
            .setLinearHeadingInterpolation(IntakeThree.getHeading(), OuttakeThree.getHeading())
            .build();
  }
}
