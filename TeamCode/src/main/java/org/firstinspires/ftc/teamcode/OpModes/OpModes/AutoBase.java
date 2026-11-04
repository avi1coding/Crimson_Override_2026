package org.firstinspires.ftc.teamcode.OpModes.OpModes;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Hardware.RobotMap;
import org.firstinspires.ftc.teamcode.SubSystems.Shooter;

public abstract class AutoBase extends OpMode {

    protected RobotMap robot;
    protected Shooter shooter;
    protected IMU imu;

    private final ElapsedTime stateTimer = new ElapsedTime();

    private enum State { UNPARK, SHOOT, PARK, DONE }
    private State state;

    private static final double SHOOT_SECONDS = 2.0;
    private static final double HEADING_KP = 0.02;
    private double phaseStartHeading = 0;
    private double phaseForward = 0;
    private double phaseStrafe = 0;
    protected abstract double getUnparkForward();
    protected abstract double getUnparkStrafe();
    protected abstract double getUnparkSeconds();

    protected abstract double getParkForward();
    protected abstract double getParkStrafe();
    protected abstract double getParkSeconds();

    protected abstract boolean shouldShootInAuto();

    @Override
    public void init() {
        robot = new RobotMap();
        robot.init(hardwareMap);

        shooter = new Shooter();
        shooter.init(hardwareMap);

        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot orientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP
        );
        imu.initialize(new IMU.Parameters(orientation));    }

    @Override
    public void start() {
        state = State.UNPARK;
        stateTimer.reset();
        beginPhase(getUnparkForward(), getUnparkStrafe());
    }

    @Override
    public void loop() {
        switch (state) {
            case UNPARK:
                driveVectorCorrected(phaseForward, phaseStrafe);
                if (stateTimer.seconds() >= getUnparkSeconds()) {
                    stopDrive();
                    stateTimer.reset();
                    if (shouldShootInAuto()) {
                        shooter.shoot();
                        state = State.SHOOT;
                    } else {
                        beginPhase(getParkForward(), getParkStrafe());
                        state = State.PARK;
                    }
                }
                break;

            case SHOOT:
                if (stateTimer.seconds() >= SHOOT_SECONDS) {
                    shooter.stop();
                    stateTimer.reset();
                    beginPhase(getParkForward(), getParkStrafe());
                    state = State.PARK;
                }
                break;

            case PARK:
                driveVectorCorrected(phaseForward, phaseStrafe);
                if (stateTimer.seconds() >= getParkSeconds()) {
                    stopDrive();
                    state = State.DONE;
                }
                break;

            case DONE:
                break;
        }

        telemetry.addData("State", state);
        telemetry.addData("Timer (s)", stateTimer.seconds());
        telemetry.addData("Heading (deg)", imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.update();
    }

    @Override
    public void stop() {
        stopDrive();
    }

    private void beginPhase(double forward, double strafe) {
        phaseForward = forward;
        phaseStrafe = strafe;
        phaseStartHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
    }

    private void driveVectorCorrected(double forward, double strafe) {
        double currentHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES);
        double error = normalizeAngle(currentHeading - phaseStartHeading);
        double turn = -HEADING_KP * error;

        double frontLeft  = forward + strafe + turn;
        double backLeft   = forward - strafe + turn;
        double frontRight = forward - strafe - turn;
        double backRight  = forward + strafe - turn;

        double max = Math.max(1.0, Math.max(Math.abs(frontLeft),
                Math.max(Math.abs(backLeft), Math.max(Math.abs(frontRight), Math.abs(backRight)))));

        robot.frontLeftMotor.setPower(frontLeft / max);
        robot.backLeftMotor.setPower(backLeft / max);
        robot.frontRightMotor.setPower(frontRight / max);
        robot.backRightMotor.setPower(backRight / max);
    }

    private void stopDrive() {
        robot.frontLeftMotor.setPower(0);
        robot.backLeftMotor.setPower(0);
        robot.frontRightMotor.setPower(0);
        robot.backRightMotor.setPower(0);
    }

    private double normalizeAngle(double degrees) {
        while (degrees > 180) degrees -= 360;
        while (degrees < -180) degrees += 360;
        return degrees;
    }
}