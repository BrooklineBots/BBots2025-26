package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSub;

public class AutoAlignCommand extends CommandBase {

    private final LimelightSub limelight;
    private final Drivetrain drive;
    private final Telemetry telemetry;

    public AutoAlignCommand(Drivetrain drive, LimelightSub limelight, Telemetry telemetry){
        this.limelight = limelight;
        this.drive = drive;
        this.telemetry = telemetry;
        addRequirements(limelight);
    }

    @Override
    public void execute() {
        double kP = 0.002;
        double error = 0;
        double lastError = 0;
        double goalX = 0;
        
        telemetry.update();
    }

    @Override
    public void end(boolean interrupted) {
        drive.stopMotors();
    }
}
