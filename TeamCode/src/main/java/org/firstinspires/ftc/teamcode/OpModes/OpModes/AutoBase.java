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

    // Each of your 4 position files fills these in with real tuned values
    protected abstract double getUnparkPower();
    protected abstract double getUnparkSeconds();
    protected abstract double getParkPower();
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
        driveAll(getUnparkPower());
    }

    @Override
    public void loop() {
        switch (state) {
            case UNPARK:
                if (stateTimer.seconds() >= getUnparkSeconds()) {
                    driveAll(0);
                    stateTimer.reset();
                    if (shouldShootInAuto()) {
                        shooter.shoot();
                        state = State.SHOOT;
                    } else {
                        driveAll(getParkPower());
                        state = State.PARK;
                    }
                }
                break;

            case SHOOT:
                if (stateTimer.seconds() >= SHOOT_SECONDS) {
                    shooter.stop();
                    stateTimer.reset();
                    driveAll(getParkPower());
                    state = State.PARK;
                }
                break;

            case PARK:
                if (stateTimer.seconds() >= getParkSeconds()) {
                    driveAll(0);
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
        driveAll(0);
    }

    private void driveAll(double power) {
        robot.frontLeftMotor.setPower(power);
        robot.backLeftMotor.setPower(power);
        robot.frontRightMotor.setPower(power);
        robot.backRightMotor.setPower(power);
    }
}