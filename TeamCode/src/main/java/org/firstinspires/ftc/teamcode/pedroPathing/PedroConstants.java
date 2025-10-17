package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Constants;

public class PedroConstants {
  public static FollowerConstants followerConstants =
      new FollowerConstants().mass(Constants.RobotConstants.ROBOT_MASS);

  public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);

  public static MecanumConstants driveConstants =
      new MecanumConstants()
          .maxPower(1)
          .rightFrontMotorName("frontRightMotor")
          .rightRearMotorName("backRightMotor")
          .leftFrontMotorName("frontLeftMotor")
          .leftRearMotorName("backLeftMotor")
          .leftFrontMotorDirection(DcMotorSimple.Direction.REVERSE)
          .leftRearMotorDirection(DcMotorSimple.Direction.REVERSE)
          .rightFrontMotorDirection(DcMotorSimple.Direction.FORWARD)
          .leftRearMotorDirection(DcMotorSimple.Direction.FORWARD);

  public static PinpointConstants localizerConstants =
      new PinpointConstants()
          .forwardPodY(Constants.RobotConstants.FORWARD_POD_OFFSET)
          .strafePodX(Constants.RobotConstants.STRAFE_POD_OFFSET)
          .distanceUnit(DistanceUnit.INCH)
          .hardwareMapName("pinpoint")
          .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD)
          .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
          .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD);

  public static Follower createFollower(final HardwareMap hardwareMap) {
    return new FollowerBuilder(followerConstants, hardwareMap)
        .pinpointLocalizer(localizerConstants)
        .pathConstraints(pathConstraints)
        .mecanumDrivetrain(driveConstants)
        .build();
  }
}
