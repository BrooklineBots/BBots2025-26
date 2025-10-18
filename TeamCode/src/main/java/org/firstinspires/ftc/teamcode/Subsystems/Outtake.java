package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Constants;

public class Outtake extends SubsystemBase {
  private final MotorEx outtakeMotor;

  private final double MAX_VELOCITY = Constants.OuttakeConstants.OUTTAKE_MAX_VELOCITY;
  private final double MIN_VELOCITY = 1;

  private final Telemetry telemetry;

  public Outtake(final HardwareMap hwMap, final Telemetry telemetry) {
    outtakeMotor = new MotorEx(hwMap, Constants.OuttakeConstants.OUTTAKE_ID);

    this.telemetry = telemetry;

    outtakeMotor.setInverted(false);
  }

  public void setVelocity(final double velocity) {
    if (velocity >= MIN_VELOCITY && velocity <= MAX_VELOCITY) {
      outtakeMotor.setVelocity(velocity, AngleUnit.RADIANS);
    }
  }

  public double getVelocity() {
    return outtakeMotor.getVelocity();
  }

  public void shoot() {
    setVelocity(Constants.OuttakeConstants.OUTTAKE_MOVEMENT_SPEED);
  }

  public void stop() {
    outtakeMotor.stopMotor();
  }
}
