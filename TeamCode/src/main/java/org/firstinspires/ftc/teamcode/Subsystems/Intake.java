package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import org.firstinspires.ftc.teamcode.Constants;

public class Intake extends SubsystemBase {
  private final Motor leftIntakeMotor;
  private final Motor rightIntakeMotor;

  public Intake(final HardwareMap hwMap) {
    leftIntakeMotor = new Motor(hwMap, Constants.IntakeConstants.LEFT_INTAKE_ID);
    rightIntakeMotor = new Motor(hwMap, Constants.IntakeConstants.RIGHT_INTAKE_ID);

    rightIntakeMotor.setInverted(true);
  }

  private void setPower(double power) {
    leftIntakeMotor.set(power);
    rightIntakeMotor.set(power);
  }

  public void intake() {
    setPower(Constants.IntakeConstants.INTAKE_POWER);
  }

  public void stop() {
    setPower(0);
  }
}
