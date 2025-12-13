package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.BombshellServo;

public class BombshellPushUpCommand extends CommandBase {
  private final BombshellServo bombshellServo;
  private long startTime;

  // 0.5 seconds

  public BombshellPushUpCommand(BombshellServo bombshellServo) {
    this.bombshellServo = bombshellServo;
    addRequirements(bombshellServo);
  }

  @Override
  public void initialize() {
    //        startTime = System.currentTimeMillis();
  }

  @Override
  public void execute() {
    bombshellServo.pushUp(1);
  }

  //    @Override
  //    public boolean isFinished() {
  ////        return System.currentTimeMillis() - startTime >=
  // Constants.BombshellServoConstants.secondsToPush;
  //    }

  @Override
  public void end(boolean interrupted) {
    //        bombshellServo.stop();
  }
}

// package org.firstinspires.ftc.teamcode.Commands;
//
// import com.seattlesolvers.solverslib.command.CommandBase;
//
// import org.firstinspires.ftc.teamcode.Subsystems.BombshellServo;
//
// public class BombshellPushUpCommand extends CommandBase {
//    private final BombshellServo bombshellServo;
//
//    public BombshellPushUpCommand(BombshellServo bombshellServo){
//        this.bombshellServo = bombshellServo;
//        addRequirements(bombshellServo);
//    }
//
//    @Override
//    public void execute(){
//        bombshellServo.pushUp();
//    }
//
//    @Override
//    public void end(boolean interrupted){
//        bombshellServo.stop();
//    }
// }
