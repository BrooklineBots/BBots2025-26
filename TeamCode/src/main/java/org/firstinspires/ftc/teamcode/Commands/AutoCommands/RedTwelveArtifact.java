package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import java.io.IOException;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Utils.PedroPathReader;

public class RedTwelveArtifact extends SequentialCommandGroup {
  private final Follower follower;

  // Poses
  PedroPathReader pp = new PedroPathReader("RedTwelveArtifact.pp");

  private final Pose startPoint = pp.get("startPoint");
  private final Pose BeforeFirstRow = pp.get("BeforeFirstRow");
  private final Pose AfterFirstRow = pp.get("AfterFirstRow");
  private final Pose Scoring_Position = pp.get("Scoring Position");

  // Path chains
  private PathChain startPointToBeforeFirstRow,
      BeforeFirstRowToAfterFirstRow,
      AfterFirstRowToScoring_Position;

  public RedTwelveArtifact(final Drivetrain drive) throws IOException {
    this.follower = drive.getFollower();
    follower.setStartingPose(startPoint);

    buildPaths();

    addCommands(
        new FollowPathCommand(follower, startPointToBeforeFirstRow),
        new FollowPathCommand(follower, BeforeFirstRowToAfterFirstRow),
        new FollowPathCommand(follower, AfterFirstRowToScoring_Position));
  }

  public void buildPaths() {
    startPointToBeforeFirstRow =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPoint, BeforeFirstRow))
            //            .setLinearHeadingInterpolation(startPoint.getHeading(),
            // BeforeFirstRow.getHeading())
            .build();
    BeforeFirstRowToAfterFirstRow =
        follower
            .pathBuilder()
            .addPath(new BezierLine(BeforeFirstRow, AfterFirstRow))
            .setLinearHeadingInterpolation(BeforeFirstRow.getHeading(), AfterFirstRow.getHeading())
            .build();

    AfterFirstRowToScoring_Position =
        follower
            .pathBuilder()
            .addPath(new BezierCurve(AfterFirstRow, Scoring_Position))
            .setLinearHeadingInterpolation(
                AfterFirstRow.getHeading(), Scoring_Position.getHeading())
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
}
