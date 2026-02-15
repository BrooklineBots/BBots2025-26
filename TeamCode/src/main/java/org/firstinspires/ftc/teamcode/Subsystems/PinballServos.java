package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import org.firstinspires.ftc.teamcode.Constants;

public class PinballServos extends SubsystemBase {
  // variable
  private ServoEx pinballRight;
  private ServoEx pinballLeft;

  public PinballServos(final HardwareMap hwMap) {
    pinballRight = new ServoEx(hwMap, Constants.PinballServosConstants.PINBALL_RIGHT_ID);
    pinballLeft = new ServoEx(hwMap, Constants.PinballServosConstants.PINBALL_LEFT_ID);
  }

  public void open() {
    pinballRight.set(Constants.PinballServosConstants.rightOpen);
    pinballLeft.set(Constants.PinballServosConstants.leftOpen);
  }

  public void close() {
    pinballRight.set(Constants.PinballServosConstants.rightClosed);
    pinballLeft.set(Constants.PinballServosConstants.leftClosed);
  }
}
