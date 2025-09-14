package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import org.firstinspires.ftc.teamcode.Constants;

public class Intake extends SubsystemBase {
  private final Motor leftIntakeMotor;
  private final Motor rightIntakeMotor;

  private final int MAX_POWER = 1;
  private final int MIN_POWER = -1;

  public Intake(final HardwareMap hwMap) {
    leftIntakeMotor = new Motor(hwMap, Constants.IntakeConstants.LEFT_INTAKE_ID);
    rightIntakeMotor = new Motor(hwMap, Constants.IntakeConstants.RIGHT_INTAKE_ID);

    rightIntakeMotor.setInverted(true);
  }

  private void setPower(double power) {
    if (power >= MIN_POWER && power <= MAX_POWER) {
      leftIntakeMotor.set(power);
      rightIntakeMotor.set(power);
    }
  }

  public void intake() {
    setPower(Constants.IntakeConstants.INTAKE_POWER);
  }

  public void stop() {
    setPower(0);
  }
}
