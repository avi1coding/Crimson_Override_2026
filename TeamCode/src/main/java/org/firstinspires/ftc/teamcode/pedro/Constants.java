package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.TwoWheelConfig;
import com.pedropathing.revhub.localizers.TwoWheelLocalizer;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Constants {

    // Requires your two dead-wheel pods physically mounted and wired into
    // encoder ports on the hub (no Pinpoint board needed).
    public static TwoWheelConfig localizerConfig = new TwoWheelConfig(
            c -> {
                c.xPodName.set("xPod");  // TODO: match whatever you name it on the Driver Station
                c.yPodName.set("yPod");  // TODO: match whatever you name it on the Driver Station

                c.xPodOffset.set(0.0);   // TODO: measure, inches from center
                c.yPodOffset.set(0.0);   // TODO: measure, inches from center

                c.xPodDirection.set(1.0); // TODO: flip to -1.0 if it reads backwards when testing
                c.yPodDirection.set(1.0); // TODO: flip to -1.0 if it reads backwards when testing

                c.forwardTicksToInches.set(0.0); // TODO: your pod's calibration (from its spec sheet)
                c.strafeTicksToInches.set(0.0);  // TODO: your pod's calibration

                c.imuName.set("imu"); // TODO: confirm this matches your hub's IMU config name
            }
    );

    public static MecanumConfig drivetrainConfig = new MecanumConfig(
            c -> {
                c.frontLeftName.set("frontLeftMotor");
                c.backLeftName.set("backLeftMotor");
                c.frontRightName.set("frontRightMotor");
                c.backRightName.set("backRightMotor");

                c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.backLeftDirection.set(DcMotorSimple.Direction.REVERSE);
                c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
                c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);

                c.manualBrakeMode.set(true);
            }
    );

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                c.forwardTranslational.set(Controller.proportional(0.3));
                c.strafeTranslational.set(Controller.proportional(0.3));

                c.coast.set(Controller.proportionalFeedforward(0.01));
                c.brake.set(Controller.proportionalFeedforward(0.01));

                c.headingFeedback.set(Controller.proportional(3.0));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.05, 0.01));

                c.linearBrakeCoefficients.set(Matrix.diag(0.1, 0.1));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.001, 0.001));

                c.maxAchievableForwardVelocity.set(60.0);
                c.maxAchievableStrafeVelocity.set(45.0);
                c.naturalForwardDeceleration.set(60.0);
                c.naturalStrafeDeceleration.set(60.0);
            }
    );

    public static Follower create(HardwareMap h) {
        return new Follower(
                new TwoWheelLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}