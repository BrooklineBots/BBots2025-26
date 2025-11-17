package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class PedroAutoTest extends SequentialCommandGroup {
  private final Follower follower;
  private final Intake intakeWheel;

  // Poses
  private final Pose startPose = newPose(56, 8, 90);
  private final Pose pose2 = newPose(39, 43, 180);
  private final Pose pose3 = newPose(12, 43, 90);
  private final Pose pose4 = newPose(56, 96, 135);
  private final Pose pose5 = newPose(37, 70, 180);
  private final Pose pose6 = newPose(13, 59, 180);
  private final Pose pose7 = newPose(56, 96, 135);
  private final Pose pose8 = newPose(34, 83, 180);
  private final Pose pose9 = newPose(14, 83, 180);
  private final Pose pose10 = newPose(56, 96, 132);
  private final Pose pose11 = newPose(12, 69, 90);

  // Path chains
  private PathChain path1, path2, path3, path4, path5, path6, path7, path8, path9, path10;

  public PedroAutoTest(final Drivetrain drive, final Intake intake) {
    this.follower = drive.getFollower();
    this.intakeWheel = intake;
    follower.setStartingPose(startPose);

    buildPaths();

    addCommands(
        new FollowPathCommand(follower, path1),
        new WaitCommand(500),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, path2),
            new IntakeCommand(intakeWheel).withTimeout(1000)),
        stopIntake(),
        new WaitCommand(500),
        new FollowPathCommand(follower, path3),
        new WaitCommand(500),
        new FollowPathCommand(follower, path4),
        new WaitCommand(500));

    /*
    -----TEMPLATES------ by Brielle
    TO ADD A PATH:
    new FollowPathCommand(follower, pathName),
    (optional) new WaitCommand(500), to allow the heading to be fully corrected before doing next movement

    FOR SIMULTANEOUS INTAKE:
    new ParallelRaceGroup(
        new FollowPathCommand(follower, pathName),
        new IntakeCommand(intakeWheel).withTimeout(milliseconds)),
    stopIntake());
     */

  }

  private InstantCommand stopIntake() {
    return new InstantCommand(() -> intakeWheel.stop(), intakeWheel);
  }

  public void buildPaths() {
    path1 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPose, pose2))
            .setLinearHeadingInterpolation(startPose.getHeading(), pose2.getHeading())
            .build();

    path2 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(pose2, pose3))
            .setTangentHeadingInterpolation()
            .build();

    path3 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(pose3, pose4))
            .setLinearHeadingInterpolation(pose3.getHeading(), pose4.getHeading())
            .build();

    path4 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(pose4, pose5))
            .setLinearHeadingInterpolation(pose4.getHeading(), pose5.getHeading())
            .build();
    path5 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(pose5, pose6))
            .setLinearHeadingInterpolation(pose5.getHeading(), pose6.getHeading())
            .build();
    path6 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(pose6, pose7))
            .setLinearHeadingInterpolation(pose6.getHeading(), pose7.getHeading())
            .build();

    path7 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(pose7, pose8))
            .setLinearHeadingInterpolation(pose7.getHeading(), pose8.getHeading())
            .build();

    path8 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(pose8, pose9))
            .setLinearHeadingInterpolation(pose8.getHeading(), pose9.getHeading())
            .build();
    path9 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(pose9, pose10))
            .setLinearHeadingInterpolation(pose9.getHeading(), pose10.getHeading())
            .build();
    path10 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(pose10, pose11))
            .setLinearHeadingInterpolation(pose10.getHeading(), pose11.getHeading())
            .build();

    /*
    -----PATHS TEMPLATE-----
    LINEAR PATH:
    pathName =
        follower
            .pathBuilder()
            .addPath(new BezierLine(firstPose, secondPose))
            .setLinearHeadingInterpolation(firstPose.getHeading(), secondPose.getHeading())
            .build();

    TANGENTIAL PATH:
    pathName =
        follower
            .pathBuilder()
            .addPath(new BezierLine(firstPose, secondPose))
            .setTangentHeadingInterpolation()
            .build();
     */
  }

  public static Pose newPose(final int x, final int y, final int heading) {
    return new Pose(y, 144 - x, Math.toRadians(heading - 90));
  }
}
