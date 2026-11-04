package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {

    private DcMotorEx shooterMotor;
    private Servo gateServo;

    private static final double SHOOTER_VELOCITY = 1800; // ticks per second, tune this later
    private static final double IDLE_VELOCITY = 400;     // ticks per second, tune this later

    private static final double GATE_OPEN_POSITION = 1.0;   // TODO: tune once mounted
    private static final double GATE_CLOSED_POSITION = 0.0; // TODO: tune once mounted

    public void init(HardwareMap hardwareMap) {
        shooterMotor = hardwareMap.get(DcMotorEx.class, "shooter");
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterMotor.setVelocity(IDLE_VELOCITY);

        gateServo = hardwareMap.get(Servo.class, "shooterGate");
        closeGate();
    }

    public void shoot() {
        shooterMotor.setVelocity(SHOOTER_VELOCITY);
    }

    public void stop() {
        shooterMotor.setVelocity(IDLE_VELOCITY);
    }

    public void openGate() {
        gateServo.setPosition(GATE_OPEN_POSITION);
    }

    public void closeGate() {
        gateServo.setPosition(GATE_CLOSED_POSITION);
    }

    public double getCurrentVelocity() {
        return shooterMotor.getVelocity() ;
    }
}