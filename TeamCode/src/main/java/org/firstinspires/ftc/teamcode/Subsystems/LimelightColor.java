package org.firstinspires.ftc.teamcode.Subsystems;

import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes.ColorResult;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.List;

public class LimelightColor extends SubsystemBase {
    private Limelight3A limelight;
    private LLResult latestResult;

    public LimelightColor(HardwareMap hardwareMap) {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        limelight.setPollRateHz(100);//
        limelight.pipelineSwitch(1); // Use pipeline 1 for green and 2 for purple
        limelight.pipelineSwitch(2); // purple
        limelight.start();
        
    }

    @Override
    public void periodic() {
        latestResult = limelight.getLatestResult();
    }

    public boolean hasTarget() {
        return latestResult != null && latestResult.isValid();
    }

    public double getTx() {
        if (hasTarget()) {
            return latestResult.getTx();
        }
        return 0;
    }

    public double getTy() {
        if (hasTarget()) {
            return latestResult.getTy();
        }
        return 0;
    }

    public double getTa() {
        if (hasTarget()) {
            return latestResult.getTa();
        }
        return 0;
    }

    public List<ColorResult> getColorResults() {
        if (hasTarget()) {
            return latestResult.getColorResults();
        }
        return null;
    }

    public int getTargetCount() {
        if (hasTarget()) {
            List<ColorResult> results = latestResult.getColorResults();
            return results != null ? results.size() : 0;
        }
        return 0;
    }
}