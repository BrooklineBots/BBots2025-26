package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class PinballServos extends SubsystemBase {
  // variable
  private ServoEx pinballRight;
  private ServoEx pinballLeft;

  private final Telemetry telemetry;

  public PinballServos(final HardwareMap hwMap, final Telemetry telemetry) {
    pinballRight = new ServoEx(hwMap, Constants.PinballServosConstants.PINBALL_RIGHT_ID);
    pinballLeft = new ServoEx(hwMap, Constants.PinballServosConstants.PINBALL_LEFT_ID);

    pinballRight.setInverted(true);
    pinballLeft.setInverted(true);
    this.telemetry = telemetry;
  }

  public void open() {
    pinballRight.set(Constants.PinballServosConstants.rightOpen);
    telemetry.addData("Right: ", pinballRight.getRawPosition());
    pinballLeft.set(Constants.PinballServosConstants.leftOpen);
    telemetry.addData("Left: ", pinballLeft.getRawPosition());
  }

  public void close() {
    pinballRight.set(Constants.PinballServosConstants.rightClosed);
    telemetry.addData("Right: ", pinballRight.getRawPosition());
    pinballLeft.set(Constants.PinballServosConstants.leftClosed);
    telemetry.addData("Left: ", pinballLeft.getRawPosition());
  }
}
