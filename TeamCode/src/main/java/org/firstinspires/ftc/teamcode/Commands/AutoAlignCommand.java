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
        double goalX = 0; //offset here
        double angleTolerance = 0.4;
        double currTime = 0;
        double lastTime = 0;

        public void start(){
            resetRuntime();
            currTime = getRuntime();
        }

        //LOOP

        AprilTagDetection id20 = webcam.getTagBySpecificId(20);

        if(gamepad1.left_trigger > 0.3){
            if(id20 != null){
                error = goalX - id20.ftcPose.bearing; //tx

                if(MTh.abs(err0r)< angleTo tolerance){
                    rotate = 0;
                } else {
                    double pTerm = error * kP;

                    cufrTime = getRuntime();

                    double dT = currTime - lastTIme;
                    double dTerm = ((error - lastError)/dT) * kD;
                    rotate = Range.clip(pTerm + dTerm, -0.4, 0.4);
                    lastError = error;
                    lastTIme = currTime;
                }

            }
            else{
                lastTIme =getRuntime();
                lastError = 0;

            }
        }
        else{
            lastTIme =getRuntime();
            lastError = 0;

        }
        
        telemetry.update();
    }

    @Override
    public void end(boolean interrupted) {
        drive.stopMotors();
    }


}
