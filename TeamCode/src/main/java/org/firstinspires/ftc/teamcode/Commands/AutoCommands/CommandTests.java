/* ============================================================= *
 *        Pedro Pathing Plus Visualizer — Auto-Generated         *
 *                                                               *
 *  Version: 1.7.3.                                              *
 *  Copyright (c) 2026 Matthew Allen                             *
 *                                                               *
 *  THIS FILE IS AUTO-GENERATED — DO NOT EDIT MANUALLY.          *
 *  Changes will be overwritten when regenerated.                *
 * ============================================================= */

package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathingplus.pathing.ProgressTracker;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitCommand;
import java.io.IOException;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.IntakeOutCommand;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;

public class CommandTests extends SequentialCommandGroup {

  private final Follower follower;
  private final ProgressTracker progressTracker;
  private final Intake intake;

  // Poses
  private Pose startPoint;
  private Pose point1;

  // Path chains
  private PathChain startPointTOpoint1;

  public CommandTests(
      final Drivetrain drive, final Intake intake, HardwareMap hw, Telemetry telemetry)
      throws IOException {
    this.follower = drive.getFollower();
    this.progressTracker = new ProgressTracker(follower, telemetry);
    this.intake = intake;

    // Load poses
    startPoint = new Pose(56.000, 8.000, Math.toRadians(90));
    point1 = new Pose(56.000, 8.000, Math.toRadians(-90));

    follower.setStartingPose(startPoint);

    buildPaths();

    addCommands(
        new ParallelRaceGroup(
            new WaitCommand(1000), new IntakeOutCommand(intake).withTimeout(1500)));
  }

  public void buildPaths() {
    startPointTOpoint1 =
        follower
            .pathBuilder()
            .addPath(new BezierLine(startPoint, point1))
            .setLinearHeadingInterpolation(Math.toRadians(-90), Math.toRadians(-90))
            .build();
  }
}
