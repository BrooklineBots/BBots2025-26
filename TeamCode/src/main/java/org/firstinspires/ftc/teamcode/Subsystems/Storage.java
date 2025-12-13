package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import org.firstinspires.ftc.teamcode.Constants;

public class Storage extends SubsystemBase {
  private final MotorEx storageMotor;

  private final double MAX_VELOCITY = Constants.StorageConstants.STORAGE_MAX_VELOCITY;
  private final double MIN_VELOCITY = 1;

  public Storage(final HardwareMap hwMap) {
    storageMotor = new MotorEx(hwMap, Constants.StorageConstants.STORAGE_MOTOR_ID);

    storageMotor.setRunMode(Motor.RunMode.VelocityControl);
  }

  private void setVelocity(final double velocity) {
    if (velocity >= MIN_VELOCITY && velocity <= MAX_VELOCITY) {
      storageMotor.setVelocity(velocity);
    }
  }

  public double getVelocity() {
    return storageMotor.getVelocity();
  }

  public void store() {
    storageMotor.setInverted(false);
    setVelocity(Constants.StorageConstants.STORAGE_VELOCITY);
  }

  public void expelStorage() {
    storageMotor.setInverted(true);
    setVelocity(Constants.StorageConstants.STORAGE_EXPEL_VELOCITY);
  }

  public void stop() {
    storageMotor.stopMotor();
  }
}
