package org.firstinspires.ftc.teamcode.Commands.AutoCommands;

import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.Drivetrain;
import org.firstinspires.ftc.teamcode.Subsystems.LimelightSub;
import java.util.function.DoubleSupplier;

public class AutoAlignCommand extends CommandBase {
    private final Drivetrain drive;
    private final LimelightSub vision;
    private final DoubleSupplier forward, strafe;

    // Tuning constants (need to doublecheck)
    private final double kP = 0.002;
    private final double kD = 0.0001;
    private final double tolerance = 0.2;
    private final double goalX = 0.0;

    private double lastError = 0;
    private double lastTime = 0;
    private final ElapsedTime timer = new ElapsedTime();

    public AutoAlignCommand(Drivetrain drive, LimelightSub vision,
                            DoubleSupplier forward, DoubleSupplier strafe) {
        this.drive = drive;
        this.vision = vision;
        this.forward = forward;
        this.strafe = strafe;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        double rotation = 0;

        if (vision.hasTarget()) {
            double error = goalX - vision.getTX();

            if (Math.abs(error) > tolerance) {
                double currentTime = timer.seconds();
                double dt = currentTime - lastTime;
                double pTerm = error * kP;
                double dTerm = (dt > 0) ? ((error - lastError) / dt) * kD : 0;

                // 40% power
                rotation = Range.clip(pTerm + dTerm, -0.4, 0.4);

                lastError = error;
                lastTime = currentTime;
            }
        } else {

            lastError = 0;
            lastTime = timer.seconds();
        }

        drive.driveFieldCentric(forward.getAsDouble(), strafe.getAsDouble(), rotation);
    }
}