package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import static org.firstinspires.ftc.teamcode.Commands.AutoCommands.BlueTwelveArtifact.newPose;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class RedLeaveLittleTri extends SequentialCommandGroup {
  private final Follower follower;

  private final Pose startPose = newPose(88, 8, 0);
  private final Pose finalParkedPose = newPose(114, 12, 0);

  private PathChain leaveTriangle;

  public RedLeaveLittleTri(final Drivetrain drive) {
    this.follower = drive.getFollower();
    follower.setStartingPose(startPose);

    buildPaths();

    addCommands(new FollowPathCommand(follower, leaveTriangle));
  }

  void buildPaths() {
    leaveTriangle =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPose, finalParkedPose))
            .setLinearHeadingInterpolation(startPose.getHeading(), finalParkedPose.getHeading())
            .build();
  }
}
