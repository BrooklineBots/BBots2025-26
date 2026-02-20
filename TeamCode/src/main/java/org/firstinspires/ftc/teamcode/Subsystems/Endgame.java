package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Constants;

public class Endgame extends SubsystemBase {
    private final MotorEx rightEndgameMotor;
    private final MotorEx leftEndgameMotor;

    public Endgame(final HardwareMap hwMap) {
        rightEndgameMotor = new MotorEx(hwMap, Constants.EndgameConstants.RIGHT_ENDGAME_ID);
        leftEndgameMotor = new MotorEx(hwMap, Constants.EndgameConstants.LEFT_ENDGAME_ID );

        rightEndgameMotor.setRunMode(Motor.RunMode.VelocityControl);
        leftEndgameMotor.setRunMode(Motor.RunMode.VelocityControl);
        rightEndgameMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        leftEndgameMotor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
    }

    private void setVelocity(final double velocity) {
        rightEndgameMotor.setVelocity(velocity);
        leftEndgameMotor.setVelocity(velocity);
    }

    public double[] getVelocity() {
        return new double[]{rightEndgameMotor.getVelocity(), leftEndgameMotor.getVelocity()};
    }

    public void raise() {
        rightEndgameMotor.setInverted(true);
        leftEndgameMotor.setInverted(true);
        setVelocity(Constants.EndgameConstants.ENDGAME_VELOCITY);
    }

    public void lower() {
        rightEndgameMotor.setInverted(true);
        leftEndgameMotor.setInverted(true);
        setVelocity(-(Constants.EndgameConstants.ENDGAME_VELOCITY));
    }

    public void stopEndgame() {
        rightEndgameMotor.stopMotor();
        leftEndgameMotor.stopMotor();
    }
}