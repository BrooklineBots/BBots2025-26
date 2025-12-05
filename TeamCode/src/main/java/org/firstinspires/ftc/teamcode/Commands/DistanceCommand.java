package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightAprilTag;

public class DistanceCommand extends CommandBase {

    private final LimelightAprilTag limelight;
    private final Telemetry telemetry;

    public DistanceCommand(LimelightAprilTag limelight, Telemetry telemetry) {
        this.limelight = limelight;
        this.telemetry = telemetry;
        addRequirements(limelight);
    }

    @Override
    public void execute() {
        double distance = limelight.getDistance();
        double tx = limelight.getTx();
        double ty = limelight.getTy();
        double ta = limelight.getTa();

        telemetry.addData("Has Target", limelight.hasTarget());
        telemetry.addData("Distance", "%.2f", distance);
        telemetry.addData("Tx", "%.2f", tx);
        telemetry.addData("Ty", "%.2f", ty);
        telemetry.addData("Ta", "%.2f", ta);
        telemetry.update();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}