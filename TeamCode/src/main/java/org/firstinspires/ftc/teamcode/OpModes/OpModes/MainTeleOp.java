package org.firstinspires.ftc.teamcode.OpModes.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import org.firstinspires.ftc.teamcode.SubSystems.Intake;
import org.firstinspires.ftc.teamcode.Hardware.RobotMap;
import org.firstinspires.ftc.teamcode.SubSystems.Shooter;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp
public class MainTeleOp extends OpMode {

    private final RobotMap robot = new RobotMap();
    private Shooter shooter;
    private Intake intake;

    private static final double DRIVE_SPEED = 0.75;

    private boolean gateOpen = false;
    private boolean lastAState = false;

    @Override
    public void init() {
        robot.init(hardwareMap);
        intake = new Intake(hardwareMap);

        shooter = new Shooter();
        shooter.init(hardwareMap);
    }

    @Override
    public void loop() {
        double y = -gamepad1.left_stick_y;
        double x = gamepad1.left_stick_x * 1.1;
        double rx = gamepad1.right_stick_x;

        double denominator =
                Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        telemetry.addData("frontLeftPower", frontLeftPower);
        telemetry.addData("frontRightPower", frontRightPower);
        telemetry.addData("backLeftPower", backLeftPower);
        telemetry.addData("backRightPower", backRightPower);

        robot.frontLeftMotor.setPower(frontLeftPower * DRIVE_SPEED);
        robot.backLeftMotor.setPower(backLeftPower * DRIVE_SPEED);
        robot.frontRightMotor.setPower(frontRightPower * DRIVE_SPEED);
        robot.backRightMotor.setPower(backRightPower * DRIVE_SPEED);

        if (gamepad1.right_bumper) {
            intake.intake();
        } else if (gamepad1.left_bumper) {
            intake.outtake();
        } else {
            intake.stop();
        }

        if (gamepad1.right_trigger > 0.5) {
            shooter.shoot();
        } else {
            shooter.stop();
        }

        if (gamepad1.a) {
            shooter.runGate();
        } else {
            shooter.stopGate();
        }
    }
}