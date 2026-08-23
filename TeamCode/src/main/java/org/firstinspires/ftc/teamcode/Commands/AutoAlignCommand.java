package org.firstinspires.ftc.teamcode.Commands;

import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSub;

public class AutoAlignCommand extends CommandBase {

    private final LimelightSub limelight;
    private final Drivetrain drive;
    private final Telemetry telemetry;
    private final ElapsedTime timer = new ElapsedTime();

    double kP = 0.002;
    double kD = 0.001;
    double error = 0;
    double lastError = 0;
    double goalX = 0; //offset here
    double angleTolerance = 0.4;
    double currentTime = 0;
    double lastTime = 0;
    double forward, strafe, rotate;

    // uses gamepad left trigger
    // deleted left trigger else

    public AutoAlignCommand(Drivetrain drive, LimelightSub limelight, Telemetry telemetry){
        this.limelight = limelight;
        this.drive = drive;
        this.telemetry = telemetry;
        addRequirements(limelight);
    }

    @Override
    public void initialize(){
        timer.reset();
        currentTime = timer.seconds();
    }

    @Override
    public void execute() {
        boolean seesTarget = limelight.hasTarget();
        if(seesTarget != true){
            error = limelight.getTx(); //tx

            if(Math.abs(error)< angleTolerance){
                rotate = 0;
            } else {
                double pTerm = error * kP;

                currentTime = timer.seconds();

                double dT = currentTime - lastTime;
                double dTerm = ((error - lastError)/dT) * kD;
                rotate = Range.clip(pTerm + dTerm, -0.4, 0.4);
                lastError = error;
                lastTime = currentTime;
            }
        }
        else{
            lastTime = timer.seconds();
            lastError = 0;
        }
        telemetry.update();
    }

    @Override
    public void end(boolean interrupted) {
        drive.stopMotors();
    }


}
