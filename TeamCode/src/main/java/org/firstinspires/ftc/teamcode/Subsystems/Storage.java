package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import org.firstinspires.ftc.teamcode.Constants;

public class Storage extends SubsystemBase {
  private final CRServoEx storageServo;

  public Storage(final HardwareMap hwMap) {
    storageServo = new CRServoEx(hwMap, Constants.StorageConstants.STORAGE_SERVO);
  }

  private void setPower(double power) {
    storageServo.set(power);
  }

  public double getPower() {
    return storageServo.get();
  }

  public void moveArtifacts() {
    setPower(Constants.StorageConstants.STORAGE_POWER);
  }

  public void stop() {
    setPower(0.0);
  }
}
