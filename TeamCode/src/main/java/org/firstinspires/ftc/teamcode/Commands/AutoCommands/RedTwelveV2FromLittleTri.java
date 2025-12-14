package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import java.io.IOException;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Utils.Pathing.ProgressTracker;
import org.firstinspires.ftc.teamcode.Utils.PedroPathReader;

public class RedTwelveV2FromLittleTri extends SequentialCommandGroup {

  private final Follower follower;
  private final ProgressTracker progressTracker;

  // Poses
  private Pose startPoint;
  private Pose OuttakePreload;
  private Pose ToIntakeOne;
  private Pose IntakeOne;
  private Pose OuttakeOne;
  private Pose OuttakeOne_control1;
  private Pose ToIntakeTwo;
  private Pose IntakeTwo;
  private Pose OuttakeTwo;
  private Pose OuttakeTwo_control1;
  private Pose ToIntakeThree;
  private Pose IntakeThree;
  private Pose OuttakeThree;

  // Path chains
  private PathChain startPointTOOuttakePreload;
  private PathChain OuttakePreloadTOToIntakeOne;
  private PathChain ToIntakeOneTOIntakeOne;
  private PathChain IntakeOneTOOuttakeOne;
  private PathChain OuttakeOneTOToIntakeTwo;
  private PathChain ToIntakeTwoTOIntakeTwo;
  private PathChain IntakeTwoTOOuttakeTwo;
  private PathChain OuttakeTwoTOToIntakeThree;
  private PathChain ToIntakeThreeTOIntakeThree;
  private PathChain IntakeThreeTOOuttakeThree;

  public RedTwelveV2FromLittleTri(final Drivetrain drive, HardwareMap hw, Telemetry telemetry)
      throws IOException {
    this.follower = drive.getFollower();
    this.progressTracker = new ProgressTracker(follower, telemetry);

    PedroPathReader pp = new PedroPathReader("RedTwelveV2FromLittleTri.pp", hw.appContext);

    // Load poses
    startPoint = pp.get("startPoint");
    OuttakePreload = pp.get("OuttakePreload");
    ToIntakeOne = pp.get("ToIntakeOne");
    IntakeOne = pp.get("IntakeOne");
    OuttakeOne = pp.get("OuttakeOne");
    OuttakeOne_control1 = pp.get("OuttakeOne_control1");
    ToIntakeTwo = pp.get("ToIntakeTwo");
    IntakeTwo = pp.get("IntakeTwo");
    OuttakeTwo = pp.get("OuttakeTwo");
    OuttakeTwo_control1 = pp.get("OuttakeTwo_control1");
    ToIntakeThree = pp.get("ToIntakeThree");
    IntakeThree = pp.get("IntakeThree");
    OuttakeThree = pp.get("OuttakeThree");

    follower.setStartingPose(startPoint);

    buildPaths();

    addCommands(
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(startPointTOOuttakePreload);
              progressTracker.setCurrentPathName("startPointTOOuttakePreload");
              progressTracker.registerEvent("ShootCenter", 1.000);
            }),
        new FollowPathCommand(follower, startPointTOOuttakePreload),
        new SequentialCommandGroup(
            new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
            new InstantCommand(
                () -> {
                  progressTracker.executeEvent("ShootCenter");
                })),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(OuttakePreloadTOToIntakeOne);
              progressTracker.setCurrentPathName("OuttakePreloadTOToIntakeOne");
            }),
        new FollowPathCommand(follower, OuttakePreloadTOToIntakeOne),
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
              progressTracker.setCurrentChain(IntakeOneTOOuttakeOne);
              progressTracker.setCurrentPathName("IntakeOneTOOuttakeOne");
              progressTracker.registerEvent("ShootCenter", 1.000);
            }),
        new FollowPathCommand(follower, IntakeOneTOOuttakeOne),
        new SequentialCommandGroup(
            new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
            new InstantCommand(
                () -> {
                  progressTracker.executeEvent("ShootCenter");
                })),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(OuttakeOneTOToIntakeTwo);
              progressTracker.setCurrentPathName("OuttakeOneTOToIntakeTwo");
            }),
        new FollowPathCommand(follower, OuttakeOneTOToIntakeTwo),
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
              progressTracker.setCurrentChain(IntakeTwoTOOuttakeTwo);
              progressTracker.setCurrentPathName("IntakeTwoTOOuttakeTwo");
              progressTracker.registerEvent("ShootCenter", 1.000);
            }),
        new FollowPathCommand(follower, IntakeTwoTOOuttakeTwo),
        new SequentialCommandGroup(
            new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
            new InstantCommand(
                () -> {
                  progressTracker.executeEvent("ShootCenter");
                })),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(OuttakeTwoTOToIntakeThree);
              progressTracker.setCurrentPathName("OuttakeTwoTOToIntakeThree");
            }),
        new FollowPathCommand(follower, OuttakeTwoTOToIntakeThree),
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
        new FollowPathCommand(follower, IntakeThreeTOOuttakeThree),
        new SequentialCommandGroup(
            new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("ShootCenter")),
            new InstantCommand(
                () -> {
                  progressTracker.executeEvent("ShootCenter");
                })));
  }

  public void buildPaths() {
    startPointTOOuttakePreload =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPoint, OuttakePreload))
            .setLinearHeadingInterpolation(startPoint.getHeading(), OuttakePreload.getHeading())
            .build();

    OuttakePreloadTOToIntakeOne =
        follower
            .pathBuilder()
            .addPath(new BezierLine(OuttakePreload, ToIntakeOne))
            .setLinearHeadingInterpolation(OuttakePreload.getHeading(), ToIntakeOne.getHeading())
            .build();

    ToIntakeOneTOIntakeOne =
        follower
            .pathBuilder()
            .addPath(new BezierLine(ToIntakeOne, IntakeOne))
            .setTangentHeadingInterpolation()
            .build();

    IntakeOneTOOuttakeOne =
        follower
            .pathBuilder()
            .addPath(new BezierCurve(IntakeOne, OuttakeOne_control1, OuttakeOne))
            .setLinearHeadingInterpolation(IntakeOne.getHeading(), OuttakeOne.getHeading())
            .build();

    OuttakeOneTOToIntakeTwo =
        follower
            .pathBuilder()
            .addPath(new BezierLine(OuttakeOne, ToIntakeTwo))
            .setLinearHeadingInterpolation(OuttakeOne.getHeading(), ToIntakeTwo.getHeading())
            .build();

    ToIntakeTwoTOIntakeTwo =
        follower
            .pathBuilder()
            .addPath(new BezierLine(ToIntakeTwo, IntakeTwo))
            .setLinearHeadingInterpolation(ToIntakeTwo.getHeading(), IntakeTwo.getHeading())
            .build();

    IntakeTwoTOOuttakeTwo =
        follower
            .pathBuilder()
            .addPath(new BezierCurve(IntakeTwo, OuttakeTwo_control1, OuttakeTwo))
            .setLinearHeadingInterpolation(IntakeTwo.getHeading(), OuttakeTwo.getHeading())
            .build();

    OuttakeTwoTOToIntakeThree =
        follower
            .pathBuilder()
            .addPath(new BezierLine(OuttakeTwo, ToIntakeThree))
            .setLinearHeadingInterpolation(OuttakeTwo.getHeading(), ToIntakeThree.getHeading())
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
