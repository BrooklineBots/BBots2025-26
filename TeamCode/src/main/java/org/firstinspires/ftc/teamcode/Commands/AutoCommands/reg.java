package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathingplus.PedroPathReader;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import java.io.IOException;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class reg extends SequentialCommandGroup {

  private final Follower follower;

  // Poses
  private Pose startPoint;
  private Pose BeforeFirstRow;
  private Pose AfterFirstRow;
  private Pose BeforeSecondRow;
  private Pose AfterSecondRow;
  private Pose ScoringPosition;

  // Path chains
  private PathChain startPointTOBeforeFirstRow;
  private PathChain BeforeFirstRowTOAfterFirstRow;
  private PathChain AfterFirstRowTOBeforeSecondRow;
  private PathChain BeforeSecondRowTOAfterSecondRow;
  private PathChain AfterSecondRowTOScoringPosition;

  public reg(final Drivetrain drive, HardwareMap hw) throws IOException {
    this.follower = drive.getFollower();

    PedroPathReader pp = new PedroPathReader("reg.pp", hw.appContext);

    startPoint = pp.get("startPoint");
    BeforeFirstRow = pp.get("BeforeFirstRow");
    AfterFirstRow = pp.get("AfterFirstRow");
    BeforeSecondRow = pp.get("BeforeSecondRow");
    AfterSecondRow = pp.get("AfterSecondRow");
    ScoringPosition = pp.get("ScoringPosition");

    follower.setStartingPose(startPoint);

    buildPaths();

    addCommands(
        new FollowPathCommand(follower, startPointTOBeforeFirstRow),
        new FollowPathCommand(follower, BeforeFirstRowTOAfterFirstRow),
        new FollowPathCommand(follower, AfterFirstRowTOBeforeSecondRow),
        new FollowPathCommand(follower, BeforeSecondRowTOAfterSecondRow),
        new FollowPathCommand(follower, AfterSecondRowTOScoringPosition));
  }

  public void buildPaths() {
    startPointTOBeforeFirstRow =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPoint, BeforeFirstRow))
            .setLinearHeadingInterpolation(startPoint.getHeading(), BeforeFirstRow.getHeading())
            .build();

    BeforeFirstRowTOAfterFirstRow =
        follower
            .pathBuilder()
            .addPath(new BezierLine(BeforeFirstRow, AfterFirstRow))
            .setTangentHeadingInterpolation()
            .build();

    AfterFirstRowTOBeforeSecondRow =
        follower
            .pathBuilder()
            .addPath(new BezierLine(AfterFirstRow, BeforeSecondRow))
            .setTangentHeadingInterpolation()
            .build();

    BeforeSecondRowTOAfterSecondRow =
        follower
            .pathBuilder()
            .addPath(new BezierLine(BeforeSecondRow, AfterSecondRow))
            .setTangentHeadingInterpolation()
            .build();

    AfterSecondRowTOScoringPosition =
        follower
            .pathBuilder()
            .addPath(new BezierLine(AfterSecondRow, ScoringPosition))
            .setTangentHeadingInterpolation()
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
