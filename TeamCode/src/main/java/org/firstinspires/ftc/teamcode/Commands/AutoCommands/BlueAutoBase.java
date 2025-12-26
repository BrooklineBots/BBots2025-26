package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathingplus.PedroPathReader;
import com.pedropathingplus.pathing.ProgressTracker;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import java.io.IOException;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class BlueAutoBase extends SequentialCommandGroup {

  private final Follower follower;
  private final ProgressTracker progressTracker;

  // Poses
  private Pose startPoint;
  private Pose Path1;
  private Pose Path2;

  // Path chains
  private PathChain startPointTOPath1;
  private PathChain Path1TOPath2;

  public BlueAutoBase(final Drivetrain drive, HardwareMap hw, Telemetry telemetry)
      throws IOException {
    this.follower = drive.getFollower();
    this.progressTracker = new ProgressTracker(follower, telemetry);

    PedroPathReader pp = new PedroPathReader("reg.pp", hw.appContext);

    // Load poses
    startPoint = pp.get("startPoint");
    Path1 = pp.get("Path1");
    Path2 = pp.get("Path2");

    follower.setStartingPose(startPoint);

    buildPaths();

    addCommands(
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(startPointTOPath1);
              progressTracker.setCurrentPathName("startPointTOPath1");
            }),
        new FollowPathCommand(follower, startPointTOPath1),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(Path1TOPath2);
              progressTracker.setCurrentPathName("Path1TOPath2");
            }),
        new FollowPathCommand(follower, Path1TOPath2));
  }

  public void buildPaths() {
    startPointTOPath1 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPoint, Path1))
            .setLinearHeadingInterpolation(startPoint.getHeading(), Path1.getHeading())
            .build();

    Path1TOPath2 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(Path1, Path2))
            .setTangentHeadingInterpolation()
            .build();
  }
}
