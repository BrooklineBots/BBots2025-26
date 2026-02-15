package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import org.firstinspires.ftc.teamcode.Constants;

public class Intake extends SubsystemBase {
  private final MotorEx intakeMotor;

  private final double MAX_VELOCITY = Constants.IntakeConstants.INTAKE_MAX_VELOCITY;
  private final double MIN_VELOCITY = 1;

  public Intake(final HardwareMap hwMap) {
    intakeMotor = new MotorEx(hwMap, Constants.IntakeConstants.INTAKE_ID);

    intakeMotor.setRunMode(Motor.RunMode.VelocityControl);
  }

  private void setVelocity(final double velocity) {
    if (velocity >= MIN_VELOCITY && velocity <= MAX_VELOCITY) {
      intakeMotor.setVelocity(velocity);
    }
  }

  public double getVelocity() {
    return intakeMotor.getVelocity();
  }

  public void intake() {
    intakeMotor.setInverted(true);
    setVelocity(Constants.IntakeConstants.INTAKE_VELOCITY);
  }

  public void expel() {
    intakeMotor.setInverted(false);
    setVelocity(Constants.IntakeConstants.EXPEL_VELOCITY);
  }

  public void stop() {
    intakeMotor.stopMotor();
  }
}
