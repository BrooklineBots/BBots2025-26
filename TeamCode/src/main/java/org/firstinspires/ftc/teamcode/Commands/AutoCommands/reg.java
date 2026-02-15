/* ============================================================= *
 *        Pedro Pathing Plus Visualizer — Auto-Generated         *
 *                                                               *
 *  Version: 1.7.2.                                              *
 *  Copyright (c) 2026 Matthew Allen                             *
 *                                                               *
 *  THIS FILE IS AUTO-GENERATED — DO NOT EDIT MANUALLY.          *
 *  Changes will be overwritten when regenerated.                *
 * ============================================================= */

package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.pedropathingplus.PedroPathReader;
import com.pedropathingplus.pathing.ProgressTracker;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.ParallelRaceGroup;
import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
import com.seattlesolvers.solverslib.command.WaitUntilCommand;
import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
import java.io.IOException;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;

public class reg extends SequentialCommandGroup {

  private final Follower follower;
  private final ProgressTracker progressTracker;

  // Poses
  private Pose startPoint;
  private Pose BeforeFirstRow;
  private Pose BeforeFirstRow_line0_control1;
  private Pose AfterFirstRow;
  private Pose BeforeSecondRow;
  private Pose AfterSecondRow;
  private Pose ScoringPosition;
  private Pose back;

  // Path chains
  private PathChain startPointTOBeforeFirstRow;
  private PathChain BeforeFirstRowTOAfterFirstRow;
  private PathChain AfterFirstRowTOBeforeSecondRow;
  private PathChain BeforeSecondRowTOAfterSecondRow;
  private PathChain AfterSecondRowTOScoringPosition;
  private PathChain ScoringPositionTOback;

  public reg(final Drivetrain drive, HardwareMap hw) throws IOException {
    this.follower = drive.getFollower();
    this.progressTracker = new ProgressTracker(follower, telemetry);

    PedroPathReader pp = new PedroPathReader("reg.pp", hw.appContext);

    // Load poses
    startPoint = pp.get("startPoint");
    BeforeFirstRow = pp.get("BeforeFirstRow");
    AfterFirstRow = pp.get("AfterFirstRow");
    BeforeSecondRow = pp.get("BeforeSecondRow");
    AfterSecondRow = pp.get("AfterSecondRow");
    ScoringPosition = pp.get("ScoringPosition");
    back = pp.get("back");

    follower.setStartingPose(startPoint);

    buildPaths();

    addCommands(
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(startPointTOBeforeFirstRow);
              progressTracker.setCurrentPathName("startPointTOBeforeFirstRow");
              progressTracker.registerEvent("IntakeOn", 0.500);
            }),
        new ParallelRaceGroup(
            new FollowPathCommand(follower, startPointTOBeforeFirstRow),
            new SequentialCommandGroup(
                new WaitUntilCommand(() -> progressTracker.shouldTriggerEvent("IntakeOn")),
                new InstantCommand(
                    () -> {
                      progressTracker.executeEvent("IntakeOn");
                    }))),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(BeforeFirstRowTOAfterFirstRow);
              progressTracker.setCurrentPathName("BeforeFirstRowTOAfterFirstRow");
            }),
        new FollowPathCommand(follower, BeforeFirstRowTOAfterFirstRow),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(AfterFirstRowTOBeforeSecondRow);
              progressTracker.setCurrentPathName("AfterFirstRowTOBeforeSecondRow");
            }),
        new FollowPathCommand(follower, AfterFirstRowTOBeforeSecondRow),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(BeforeSecondRowTOAfterSecondRow);
              progressTracker.setCurrentPathName("BeforeSecondRowTOAfterSecondRow");
            }),
        new FollowPathCommand(follower, BeforeSecondRowTOAfterSecondRow),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(AfterSecondRowTOScoringPosition);
              progressTracker.setCurrentPathName("AfterSecondRowTOScoringPosition");
            }),
        new FollowPathCommand(follower, AfterSecondRowTOScoringPosition),
        new InstantCommand(
            () -> {
              progressTracker.setCurrentChain(ScoringPositionTOback);
              progressTracker.setCurrentPathName("ScoringPositionTOback");
            }),
        new FollowPathCommand(follower, ScoringPositionTOback));
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

    ScoringPositionTOback =
        follower
            .pathBuilder()
            .addPath(new BezierLine(ScoringPosition, back))
            .setTangentHeadingInterpolation()
            .setReversed()
            .build();
  }
}
