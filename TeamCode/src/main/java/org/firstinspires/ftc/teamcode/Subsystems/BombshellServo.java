package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class BombshellServo extends SubsystemBase {
  private final CRServoEx bombshellServo;
  private final Telemetry telemetry;

  public BombshellServo(final HardwareMap hwMap, final Telemetry telemetry) {
    bombshellServo = new CRServoEx(hwMap, Constants.BombshellServoConstants.BOMBSHELL_SERVO_ID);
    this.telemetry = telemetry;
    bombshellServo.setInverted(false);
  }

  public void setPower(double power) {
    bombshellServo.set(power);
  }

  public void pushUp() {
    setPower(1);
  }

  public void stop() {
    bombshellServo.stop();
  }
}

// package org.firstinspires.ftc.teamcode.Subsystems;
//
// import com.qualcomm.robotcore.hardware.HardwareMap;
// import com.seattlesolvers.solverslib.command.SubsystemBase;
// import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
// import org.firstinspires.ftc.robotcore.external.Telemetry;
// import org.firstinspires.ftc.teamcode.Constants;
//
// public class BombshellServo extends SubsystemBase {
//  private final ServoEx bombshellServo;
//  private final Telemetry telemetry;
//
//  public BombshellServo(final HardwareMap hwMap, final Telemetry telemetry) {
//    bombshellServo = new ServoEx(hwMap, Constants.BombshellServoConstants.BOMBSHELL_SERVO_ID);
//    this.telemetry = telemetry;
//
//    bombshellServo.setInverted(true);
//  }
//
//  private void setPosition(double position) {
//    bombshellServo.set(position);
//    telemetry.addData("Servo position: ", position);
//    telemetry.update();
//  }
//
//  public void pushUp(int pushCount) {
//    telemetry.addData("pushcount ", pushCount);
//    if (pushCount == 1) {
//      setPosition(Constants.BombshellServoConstants.servoPosition1);
//    } else if (pushCount == 2) {
//      setPosition(Constants.BombshellServoConstants.servoPosition2);
//    }
//  }
//
//  //
//  //    public void stop(){
//  //        bombshellServo.stop();
//  //    }
//
// }
