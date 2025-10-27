package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class PedroAutoTest extends SequentialCommandGroup {
  private final Follower follower;

  // Poses
  private final Pose startPose = new Pose(56, 32, Math.toRadians(90));
  private final Pose pose2 = new Pose(56, 56, Math.toRadians(90));
  private final Pose endPose = new Pose(84, 84, Math.toRadians(90));

  // Path chains
  private PathChain pathLine1, pathLine2;

  public PedroAutoTest(final Drivetrain drive) {
    this.follower = drive.getFollower();
    follower.setStartingPose(startPose);

    buildPaths();

    addCommands(
        new FollowPathCommand(follower, pathLine1)); // new FollowPathCommand(follower, pathLine2));
  }

  public void buildPaths() {
    pathLine1 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPose, pose2))
            .setLinearHeadingInterpolation(startPose.getHeading(), pose2.getHeading())
            .build();

    //    pathLine2 =
    //        follower
    //            .pathBuilder()
    //            .addPath(new BezierLine(pose2, endPose))
    //            .setLinearHeadingInterpolation(pose2.getHeading(), endPose.getHeading())
    //            .build();
  }
}
