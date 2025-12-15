package org.firstinspires.ftc.teamcode.Commands;

import com.seattlesolvers.solverslib.command.CommandBase;
import org.firstinspires.ftc.teamcode.Subsystems.BombshellServo;

public class BombshellReverseCommand extends CommandBase {
  private final BombshellServo bombshellServo;

  public BombshellReverseCommand(BombshellServo bombshellServo) {
    this.bombshellServo = bombshellServo;
    addRequirements(bombshellServo);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    bombshellServo.reverse();
  }

  @Override
  public void end(boolean interrupted) {
    bombshellServo.stop();
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
