package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.CommandScheduler;

@Autonomous(name = "Main Autonomous")
public class AutoMain extends CommandOpMode {
  private RobotContainer robotContainer;

  @Override
  public void initialize() {
    robotContainer = new RobotContainer(hardwareMap, gamepad1, gamepad2, telemetry, this);
    robotContainer.configureAuto();
  }

  @Override
  public void initialize_loop() {
    CommandScheduler.getInstance().run();
    super.initialize_loop();
  }

  @Override
  public void run() {
    CommandScheduler.getInstance().run();
    robotContainer.run();
  }
}
