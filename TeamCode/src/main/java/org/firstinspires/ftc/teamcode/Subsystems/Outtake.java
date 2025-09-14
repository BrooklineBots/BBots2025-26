package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import org.firstinspires.ftc.teamcode.Constants;

public class Outtake extends SubsystemBase {
  private final Motor leftOuttakeMotor;
  private final Motor rightOuttakeMotor;

  private final int MAX_POWER = 1;
  private final int MIN_POWER = -1;

  public Outtake(HardwareMap hwMap) {
    leftOuttakeMotor = new Motor(hwMap, Constants.OuttakeConstants.LEFT_OUTTAKE_ID);
    rightOuttakeMotor = new Motor(hwMap, Constants.OuttakeConstants.RIGHT_OUTTAKE_ID);

    leftOuttakeMotor.setInverted(true);
  }

  public void setPower(double power) {
    if (power >= MIN_POWER && power <= MAX_POWER) {
      leftOuttakeMotor.set(power);
      rightOuttakeMotor.set(power);
    }
  }

  public void shoot() {
    setPower(Constants.OuttakeConstants.OUTTAKE_POWER);
  }

  public void stop() {
    setPower(0);
  }
}
