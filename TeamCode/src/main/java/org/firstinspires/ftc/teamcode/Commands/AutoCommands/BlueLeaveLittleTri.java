package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import static org.firstinspires.ftc.teamcode.Commands.AutoCommands.BlueTwelveArtifact.newPose;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class BlueLeaveLittleTri extends SequentialCommandGroup {
  private final Follower follower;

  private final Pose startPose = newPose(56, 8, 180);
  private final Pose finalParkedPose = newPose(25, 12, 180);

  private PathChain leaveTriangle;

  public BlueLeaveLittleTri(final Drivetrain drive) {
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
