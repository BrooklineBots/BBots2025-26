package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import org.firstinspires.ftc.teamcode.Constants;

public class Intake extends SubsystemBase {
  private final MotorEx leftIntakeMotor;
  private final MotorEx rightIntakeMotor;

  private final double MAX_VELOCITY = Constants.IntakeConstants.INTAKE_MAX_VELOCITY;
  private final double MIN_VELOCITY = 1;

  public Intake(final HardwareMap hwMap) {
    leftIntakeMotor = new MotorEx(hwMap, Constants.IntakeConstants.LEFT_INTAKE_ID);
    rightIntakeMotor = new MotorEx(hwMap, Constants.IntakeConstants.RIGHT_INTAKE_ID);

    leftIntakeMotor.setRunMode(Motor.RunMode.VelocityControl);
    rightIntakeMotor.setRunMode(Motor.RunMode.VelocityControl);

    leftIntakeMotor.setInverted(false);
    rightIntakeMotor.setInverted(true);
  }

  private void setVelocity(final double velocity) {
    if (velocity >= MIN_VELOCITY && velocity <= MAX_VELOCITY) {
      leftIntakeMotor.setVelocity(velocity);
      rightIntakeMotor.setVelocity(velocity);
    }
  }

  public double getVelocity() {
    return leftIntakeMotor.getVelocity();
  }

  public void intake() {
    setVelocity(Constants.IntakeConstants.INTAKE_VELOCITY);
  }

  public void stop() {
    leftIntakeMotor.stopMotor();
    rightIntakeMotor.stopMotor();
  }
}
