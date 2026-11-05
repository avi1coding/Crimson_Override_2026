package org.firstinspires.ftc.teamcode.OpModes.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.RobotMap;

@Autonomous(name = "Calibration - Strafe")
public class CalibrationTestStrafe extends OpMode {

    private RobotMap robot;
    private final ElapsedTime timer = new ElapsedTime();

    private static final double POWER = 0.5;
    private static final double SECONDS = 2.0;

    private boolean finished = false;

    @Override
    public void init() {
        robot = new RobotMap();
        robot.init(hardwareMap);
        telemetry.addLine("Mark the robot's starting position before pressing play.");
        telemetry.update();
    }

    @Override
    public void start() {
        timer.reset();
        driveVector(0, POWER);
    }

    @Override
    public void loop() {
        if (!finished && timer.seconds() >= SECONDS) {
            driveVector(0, 0);
            finished = true;
        }

        if (finished) {
            telemetry.addLine("Done. Measure the SIDEWAYS distance traveled from the start mark.");
            telemetry.addData("Power used", POWER);
            telemetry.addData("Time used (s)", SECONDS);
        } else {
            telemetry.addData("Strafing", "%.1f / %.1f sec", timer.seconds(), SECONDS);
        }
        telemetry.update();
    }

    private void driveVector(double forward, double strafe) {
        double frontLeft  = forward + strafe;
        double backLeft   = forward - strafe;
        double frontRight = forward - strafe;
        double backRight  = forward + strafe;

        robot.frontLeftMotor.setPower(frontLeft);
        robot.backLeftMotor.setPower(backLeft);
        robot.frontRightMotor.setPower(frontRight);
        robot.backRightMotor.setPower(backRight);
    }
}