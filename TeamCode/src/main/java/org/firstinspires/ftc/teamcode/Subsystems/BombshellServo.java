package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Constants;

public class BombshellServo extends SubsystemBase {
    private final Servo bombshellServo;
    private final Telemetry telemetry;


    public BombshellServo(final HardwareMap hwMap, final Telemetry telemetry){
     bombshellServo = hwMap.get(Servo.class, Constants.BombshellServoConstants.BOMBSHELL_SERVO_ID);
     this.telemetry = telemetry;

    }

    private void setPosition(double position){
        bombshellServo.setPosition(position);
        telemetry.addData("Servo position: ", position);
        telemetry.update();
    }

    public void pushUp(int pushCount){
        telemetry.addData("pushcount ", pushCount);
        if(pushCount == 1){
            bombshellServo.setDirection(Servo.Direction.FORWARD);
            setPosition(Constants.BombshellServoConstants.servoPosition1);
        } else if(pushCount == 2){
            bombshellServo.setDirection(Servo.Direction.REVERSE);
            setPosition(Constants.BombshellServoConstants.servoPosition2);
        }


    }

//
//    public void stop(){
//        bombshellServo.stop();
//    }


}
