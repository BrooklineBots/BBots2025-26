package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSub;

public class TrackYellowBallCommand extends CommandBase {

    private final LimelightSub limelight;
    private final Drivetrain drive;
    private final Telemetry telemetry;
    private LimelightSub.Pipeline initialPipeline;

    public TrackYellowBallCommand(
            Drivetrain drive, LimelightSub limelight, Telemetry telemetry){
        this.limelight = limelight;
        this.drive = drive;
        this.telemetry = telemetry;
        addRequirements(limelight);
        initialPipeline = limelight.getPipeline();
        limelight.switchPipeline(LimelightSub.Pipeline.COLOR);
    }

    @Override
    public void execute() {

        double ta = limelight.getTa();
        double tx = limelight.getTx();
        boolean hasTarget = limelight.hasTarget();

        double txTolerance = 5.0;
        double taTolerance = 8.0;

        boolean spotOnStrafe = tx > -1 * txTolerance && tx < txTolerance;
        boolean spotOnForward = ta > taTolerance;

        double forwardSpeed = hasTarget && !spotOnForward ? -0.15 : 0;
        double strafeSpeed = hasTarget && !spotOnStrafe && !spotOnForward ? tx * -0.03 : 0;

        if (hasTarget && (!spotOnStrafe || !spotOnForward)) {
            drive.driveRobotCentric(forwardSpeed, strafeSpeed, 0);
        } else {
            drive.stopMotors();
        }

        telemetry.addData("In Yellow Ball Tracker Command", true);
        telemetry.addData("has target?", hasTarget);
        telemetry.addData("TX", tx);
        telemetry.addData("TA", ta);
        telemetry.addData("strafe speed", strafeSpeed);
        telemetry.addData("forward speed", forwardSpeed);

        if (!hasTarget) {
            telemetry.addData("Target strafe", "No target found");
            telemetry.addData("Target forward", "No target found");
        } else {
            if (tx < -txTolerance) {
                telemetry.addData("Target strafe", "to robot's left");
            } else if (tx > txTolerance) {
                telemetry.addData("Target strafe", "to robot's right");
            } else {
                telemetry.addData("Target strafe", "spot on");
            }

            if (spotOnForward) {
                telemetry.addData("Target forward", "close enough");
            } else {
                telemetry.addData("Target forward", "too far away");
            }
        }

        telemetry.update();
    }

    @Override
    public void end(boolean interrupted) {
        drive.stopMotors();
        limelight.switchPipeline(initialPipeline);
    }
}
