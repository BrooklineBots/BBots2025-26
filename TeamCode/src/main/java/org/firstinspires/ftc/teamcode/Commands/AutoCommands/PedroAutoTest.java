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
  private final Pose startPose = linearPose(64, 8, 0);
  private final Pose pose2 = linearPose(64, 35, -90); // x is forward for pedropathing
  private final Pose endPose = tangentialPose(16, 35);

  // Path chains
  private PathChain pathLine1, pathLine2;

  public PedroAutoTest(final Drivetrain drive, final Intake intake) {
    this.follower = drive.getFollower();
    this.intakeWheel = intake;
    follower.setStartingPose(startPose);

    buildPaths();

    addCommands(
        new FollowPathCommand(follower, pathLine1),
        new WaitCommand(1000),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, pathLine2),
            new IntakeCommand(intakeWheel).withTimeout(1000)),
        stopIntake());
  }

  private InstantCommand stopIntake() {
    return new InstantCommand(() -> intakeWheel.stop(), intakeWheel);
  }

  public void buildPaths() {
    pathLine1 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPose, pose2))
            .setLinearHeadingInterpolation(startPose.getHeading(), pose2.getHeading())
            .build();
    pathLine2 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(pose2, endPose))
            .setTangentHeadingInterpolation()
            .build();
  }

  public static Pose linearPose(final int x, final int y, final int heading) {
    return new Pose(y, 144 - x, Math.toRadians(-heading));
  }

  public static Pose tangentialPose(final int x, final int y) {
    return new Pose(y, 144 - x);
  }
}
