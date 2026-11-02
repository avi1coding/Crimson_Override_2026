package org.firstinspires.ftc.teamcode.OpModes.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.RobotMap;
import org.firstinspires.ftc.teamcode.SubSystems.Shooter;

public abstract class AutoBase extends OpMode {

    protected RobotMap robot;
    protected Shooter shooter;

    private final ElapsedTime stateTimer = new ElapsedTime();

    private enum State { UNPARK, SHOOT, PARK, DONE }
    private State state;

    private static final double SHOOT_SECONDS = 2.0;

    // forward: + is forward, - is backward.
    // strafe: + is right, - is left. (If it drives the wrong way when you test it,
    // just flip the sign — same idea as flipping xPodDirection earlier.)
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
    }

    @Override
    public void start() {
        state = State.UNPARK;
        stateTimer.reset();
        driveVector(getUnparkForward(), getUnparkStrafe());
    }

    @Override
    public void loop() {
        switch (state) {
            case UNPARK:
                if (stateTimer.seconds() >= getUnparkSeconds()) {
                    driveVector(0, 0);
                    stateTimer.reset();
                    if (shouldShootInAuto()) {
                        shooter.shoot();
                        state = State.SHOOT;
                    } else {
                        driveVector(getParkForward(), getParkStrafe());
                        state = State.PARK;
                    }
                }
                break;

            case SHOOT:
                if (stateTimer.seconds() >= SHOOT_SECONDS) {
                    shooter.stop();
                    stateTimer.reset();
                    driveVector(getParkForward(), getParkStrafe());
                    state = State.PARK;
                }
                break;

            case PARK:
                if (stateTimer.seconds() >= getParkSeconds()) {
                    driveVector(0, 0);
                    state = State.DONE;
                }
                break;

            case DONE:
                break;
        }

        telemetry.addData("State", state);
        telemetry.addData("Timer (s)", stateTimer.seconds());
        telemetry.update();
    }

    @Override
    public void stop() {
        driveVector(0, 0);
    }

    private void driveVector(double forward, double strafe) {
        double frontLeft  = forward + strafe;
        double backLeft   = forward - strafe;
        double frontRight = forward - strafe;
        double backRight  = forward + strafe;

        double max = Math.max(1.0, Math.max(Math.abs(frontLeft),
                Math.max(Math.abs(backLeft), Math.max(Math.abs(frontRight), Math.abs(backRight)))));

        robot.frontLeftMotor.setPower(frontLeft / max);
        robot.backLeftMotor.setPower(backLeft / max);
        robot.frontRightMotor.setPower(frontRight / max);
        robot.backRightMotor.setPower(backRight / max);
    }
}