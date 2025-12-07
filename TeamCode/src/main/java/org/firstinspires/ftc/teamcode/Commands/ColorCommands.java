package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.Subsystem;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.seattlesolvers.solverslib.command.CommandBase;
import com.qualcomm.hardware.limelightvision.LLResultTypes.ColorResult;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.util.List;

public class ColorCommands extends CommandBase {

    private Limelight3A limelightCol;
    private Telemetry telemetry;

    public ColorCommands(Limelight3A limelightCol, Telemetry telemetry) {
        this.limelightCol = limelightCol;
        this.telemetry = telemetry;
        addRequirements((Subsystem) limelightCol);
    }

    @Override
    public void execute() {
        LLResult result = limelightCol.getLatestResult();
        boolean hasTarget = result != null && result.isValid();

        telemetry.addData("Has Target", hasTarget);

        if (hasTarget) {
            telemetry.addData("Tx", "%.2f", result.getTx());
            telemetry.addData("Ty", "%.2f", result.getTy());
            telemetry.addData("Ta", "%.2f", result.getTa());

            List<ColorResult> colorResults = result.getColorResults();
            if (colorResults != null) {
                telemetry.addData("Target Count", colorResults.size());
                int i = 0;
                for (ColorResult target : colorResults) {
                    telemetry.addData("Target " + i, "X:%.2f Y:%.2f Area:%.2f",
                            target.getTargetXDegrees(),
                            target.getTargetYDegrees(),
                            target.getTargetArea());
                    i++;
                }
            }
        } else {
            telemetry.addData("Status", "No targets");
        }

        telemetry.update();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}