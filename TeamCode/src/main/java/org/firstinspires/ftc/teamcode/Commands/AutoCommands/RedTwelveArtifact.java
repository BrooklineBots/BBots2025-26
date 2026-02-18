package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelCommandGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import org.firstinspires.ftc.teamcode.Commands.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

/*
I F   Y O U   A R E   H A I L E Y,   D O N ' T   L O O K!!
(if you finished or need help, then scroll down to check ur work)
our values may differ by a little, but as long as ur code works on the robot its fine
 */

public class RedTwelveArtifact extends SequentialCommandGroup {
  private final Follower follower;
  private final Intake intakeWheel;

  // Poses
  private final Pose startPose = newPose(88, 8, 90);
  private final Pose outtakePreload = newPose(96, 96, 45);
  private final Pose beforeIntake1Pose = newPose(90, 31, 0);
  private final Pose afterIntake1Pose = newPose(125, 31, 0);
  private final Pose curveToOuttake1Pose = newPose(70, 46, 0);
  private final Pose outtake1Pose = newPose(96, 96, 45);
  private final Pose beforeIntake2Pose = newPose(90, 53, 0);
  private final Pose afterIntake2Pose = newPose(125, 53, 0);
  private final Pose curveToOuttake2Pose = newPose(80, 58, 0);
  private final Pose outtake2Pose = newPose(96, 96, 45);
  private final Pose beforeIntake3Pose = newPose(90, 78, 0);
  private final Pose afterIntake3Pose = newPose(120, 78, 0);
  private final Pose outtake3Pose = newPose(96, 96, 45);
  private final Pose releasePose = newPose(119, 69, 0);

  // shoots well 40 inches away from the basket

  // Path chains
  private PathChain setupIntake1Path,
      scorePreload,
      intake1Path,
      goToOuttake1Path,
      setupIntake2Path,
      intake2Path,
      goToOuttake2Path,
      setupIntake3Path,
      intake3Path,
      goToOuttake3Path,
      releasePath;

  public RedTwelveArtifact(final Drivetrain drive, final Intake intake) {
    this.follower = drive.getFollower();
    this.intakeWheel = intake;
    follower.setStartingPose(startPose);

    buildPaths();

    addCommands(
        new FollowPathCommand(follower, scorePreload),
        new WaitCommand(1000),
        new FollowPathCommand(follower, setupIntake1Path),
        new WaitCommand(500),
        intakeWhileRunning(intake1Path),
        stopIntake(),
        new WaitCommand(500),
        new FollowPathCommand(follower, goToOuttake1Path),
        new WaitCommand(1000),
        new FollowPathCommand(follower, setupIntake2Path),
        new WaitCommand(500),
        intakeWhileRunning(intake2Path),
        stopIntake(),
        new FollowPathCommand(follower, goToOuttake2Path),
        new WaitCommand(1000),
        new FollowPathCommand(follower, setupIntake3Path),
        new WaitCommand(500),
        intakeWhileRunning(intake3Path),
        stopIntake(),
        new FollowPathCommand(follower, goToOuttake3Path),
        new WaitCommand(1000),
        new FollowPathCommand(follower, releasePath),
        new WaitCommand(500));

    /*
    -----TEMPLATES------ by Brielle
    TO ADD A PATH:
    new FollowPathCommand(follower, pathName),
    (optional) new WaitCommand(500), to allow the heading to be fully corrected before doing next movement
    FOR SIMULTANEOUS INTAKE:
    intakeWhileRunning(pathName),
    stopIntake(),
     */

  }

  private InstantCommand stopIntake() {
    return new InstantCommand(() -> intakeWheel.stop(), intakeWheel);
  }

  private ParallelCommandGroup intakeWhileRunning(final PathChain path) {
    return new ParallelCommandGroup(
        new FollowPathCommand(follower, path), new IntakeOutCommand(intakeWheel).withTimeout(1500));
  }

  public void buildPaths() {
    scorePreload =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPose, outtakePreload))
            .setLinearHeadingInterpolation(startPose.getHeading(), outtakePreload.getHeading())
            .build();
    setupIntake1Path =
        follower
            .pathBuilder()
            .addPath(new BezierLine(outtakePreload, beforeIntake1Pose))
            .setLinearHeadingInterpolation(
                outtakePreload.getHeading(), beforeIntake1Pose.getHeading())
            .build();

    intake1Path =
        follower
            .pathBuilder()
            .addPath(new BezierLine(beforeIntake1Pose, afterIntake1Pose))
            .setTangentHeadingInterpolation()
            .build();

    goToOuttake1Path =
        follower
            .pathBuilder()
            .addPath(new BezierCurve(afterIntake1Pose, curveToOuttake1Pose, outtake1Pose))
            .setLinearHeadingInterpolation(afterIntake1Pose.getHeading(), outtake1Pose.getHeading())
            .build();

    setupIntake2Path =
        follower
            .pathBuilder()
            .addPath(new BezierLine(outtake1Pose, beforeIntake2Pose))
            .setLinearHeadingInterpolation(
                outtake1Pose.getHeading(), beforeIntake2Pose.getHeading())
            .build();
    intake2Path =
        follower
            .pathBuilder()
            .addPath(new BezierLine(beforeIntake2Pose, afterIntake2Pose))
            .setLinearHeadingInterpolation(
                beforeIntake2Pose.getHeading(), afterIntake2Pose.getHeading())
            .build();
    goToOuttake2Path =
        follower
            .pathBuilder()
            .addPath(new BezierCurve(afterIntake2Pose, curveToOuttake2Pose, outtake2Pose))
            .setLinearHeadingInterpolation(afterIntake2Pose.getHeading(), outtake2Pose.getHeading())
            .build();

    setupIntake3Path =
        follower
            .pathBuilder()
            .addPath(new BezierLine(outtake2Pose, beforeIntake3Pose))
            .setLinearHeadingInterpolation(
                outtake2Pose.getHeading(), beforeIntake3Pose.getHeading())
            .build();

    intake3Path =
        follower
            .pathBuilder()
            .addPath(new BezierLine(beforeIntake3Pose, afterIntake3Pose))
            .setLinearHeadingInterpolation(
                beforeIntake3Pose.getHeading(), afterIntake3Pose.getHeading())
            .build();
    goToOuttake3Path =
        follower
            .pathBuilder()
            .addPath(new BezierLine(afterIntake3Pose, outtake3Pose))
            .setLinearHeadingInterpolation(afterIntake3Pose.getHeading(), outtake3Pose.getHeading())
            .build();
    releasePath =
        follower
            .pathBuilder()
            .addPath(new BezierLine(outtake3Pose, releasePose))
            .setLinearHeadingInterpolation(outtake3Pose.getHeading(), releasePose.getHeading())
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
    CURVE PATH:
    pathName =
        follower
            .pathBuilder()
            .addPath(new BezierCurve(firstPose, curveControlPoint, secondPose))
            .setLinearHeadingInterpolation(firstPose.getHeading(), secondPose.getHeading())
            .build();
     */
  }

  public static Pose newPose(final int x, final int y, final int heading) {
    return new Pose(y, 144 - x, Math.toRadians(heading - 90));
  }
}
