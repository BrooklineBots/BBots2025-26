//package org.firstinspires.ftc.teamcode.Commands.AutoCommands;
//
//import com.pedropathing.follower.Follower;
//import com.pedropathing.geometry.BezierLine;
//import com.pedropathing.geometry.Pose;
//import com.pedropathing.paths.PathChain;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//import com.seattlesolvers.solverslib.command.InstantCommand;
//import com.seattlesolvers.solverslib.command.SequentialCommandGroup;
//import com.seattlesolvers.solverslib.pedroCommand.FollowPathCommand;
//import java.io.IOException;
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
//import org.firstinspires.ftc.teamcode.Utils.Pathing.ProgressTracker;
//import org.firstinspires.ftc.teamcode.Utils.PedroPathReader;
//
//public class BlueLeaveFromDepot extends SequentialCommandGroup {
//
//    private final Follower follower;
//    private final ProgressTracker progressTracker;
//
//    // Poses
//    private Pose startPoint;
//    private Pose Path1;
//
//    // Path chains
//    private PathChain startPointTOPath1;
//
//    public BlueLeaveFromDepot(final Drivetrain drive, HardwareMap hw, Telemetry telemetry)
//            throws IOException {
//        this.follower = drive.getFollower();
//        this.progressTracker = new ProgressTracker(follower, telemetry);
//
//        PedroPathReader pp = new PedroPathReader("reg.pp", hw.appContext);
//
//        // Load poses
//        startPoint = pp.get("startPoint");
//        Path1 = pp.get("Path1");
//
//        follower.setStartingPose(startPoint);
//
//        buildPaths();
//
//        addCommands(
//                new InstantCommand(() -> {
//                    progressTracker.setCurrentChain(startPointTOPath1);
//                    progressTracker.setCurrentPathName("startPointTOPath1");
//                }),
//                new FollowPathCommand(follower, startPointTOPath1)
//        );
//    }
//
//    public void buildPaths() {
//        startPointTOPath1 = follower
//                .pathBuilder()
//                .addPath(new BezierLine(startPoint, Path1))
//                .setLinearHeadingInterpolation(
//                        startPoint.getHeading(),
//                        Path1.getHeading()
//                )
//                .build();
//    }
//}
