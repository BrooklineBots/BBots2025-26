package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class Outtake extends SubsystemBase {
  private final MotorEx leftOuttakeMotor;
  private final MotorEx rightOuttakeMotor;

  private final double MAX_VELOCITY = Constants.OuttakeConstants.OUTTAKE_MAX_VELOCITY;
  private final double MIN_VELOCITY = 0;

  private final Telemetry telemetry;

  public Outtake(final HardwareMap hwMap, final Telemetry telemetry) {
    leftOuttakeMotor = new MotorEx(hwMap, Constants.OuttakeConstants.LEFT_OUTTAKE_ID);
    rightOuttakeMotor = new MotorEx(hwMap, Constants.OuttakeConstants.RIGHT_OUTTAKE_ID);

    this.telemetry = telemetry;
    // leftOuttakeMotor.setInverted(true);
    leftOuttakeMotor.setInverted(true);
    rightOuttakeMotor.setInverted(true);
  }

  public void setVelocity(final double velocity) {
    if (velocity >= MIN_VELOCITY && velocity <= MAX_VELOCITY) {
      leftOuttakeMotor.setVelocity(velocity);
      rightOuttakeMotor.setVelocity(velocity);
    }
  }

  public double getVelocity() {
    return leftOuttakeMotor.getVelocity();
  }

  public void shoot() {
    setVelocity(Constants.OuttakeConstants.OUTTAKE_MAX_VELOCITY);
  }

  public void stop() {
    setVelocity(0);
  }
}
