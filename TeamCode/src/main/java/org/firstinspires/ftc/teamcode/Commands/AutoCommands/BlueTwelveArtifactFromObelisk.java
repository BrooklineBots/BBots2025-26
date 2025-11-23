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
import org.firstinspires.ftc.teamcode.Commands.IntakeCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class BlueTwelveArtifactFromObelisk extends SequentialCommandGroup {
  private final Follower follower;
  private final Intake intakeWheel;
  // Poses
  private final Pose startPose = new Pose(25, 130, 325);
  private final Pose outtakePreload = new Pose(48, 96, 135);
  private final Pose beforeIntake1Pose = new Pose(42, 44, 180);
  private final Pose afterIntake1Pose = new Pose(5, 44, 180);
  private final Pose curveToOuttake1Pose = new Pose(64, 46, 180);
  private final Pose outtake1Pose = new Pose(48, 96, 135);
  private final Pose beforeIntake2Pose = new Pose(42, 68, 180);
  private final Pose afterIntake2Pose = new Pose(5, 68, 180);
  private final Pose curveToOuttake2Pose = new Pose(60, 58, 180);
  private final Pose outtake2Pose = new Pose(48, 96, 135);
  private final Pose beforeIntake3Pose = new Pose(42, 90, 180);
  private final Pose afterIntake3Pose = new Pose(13, 90, 180);
  private final Pose outtake3Pose = new Pose(48, 96, 135);
  private final Pose releasePose = new Pose(25, 69, 90);

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

  public BlueTwelveArtifactFromObelisk(final Drivetrain drive, final Intake intake) {
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
        new FollowPathCommand(follower, goToOuttake1Path),
        new WaitCommand(500),
        new FollowPathCommand(follower, setupIntake2Path),
        new WaitCommand(500),
        new FollowPathCommand(follower, intake2Path),
        new WaitCommand(1000),
        intakeWhileRunning(intake1Path),
        stopIntake(),
        new FollowPathCommand(follower, goToOuttake2Path),
        new WaitCommand(500),
        new FollowPathCommand(follower, setupIntake3Path),
        new WaitCommand(500),
        new FollowPathCommand(follower, intake3Path),
        new WaitCommand(1000),
        intakeWhileRunning(intake1Path),
        stopIntake(),
        new FollowPathCommand(follower, goToOuttake3Path),
        new WaitCommand(500),
        new FollowPathCommand(follower, releasePath),
        new WaitCommand(500));
  }

  private InstantCommand stopIntake() {
    return new InstantCommand(() -> intakeWheel.stop(), intakeWheel);
  }

  private ParallelCommandGroup intakeWhileRunning(final PathChain path) {
    return new ParallelCommandGroup(
        new FollowPathCommand(follower, path), new IntakeCommand(intakeWheel).withTimeout(1500));
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
            .setLinearHeadingInterpolation(
                beforeIntake1Pose.getHeading(), afterIntake1Pose.getHeading())
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
