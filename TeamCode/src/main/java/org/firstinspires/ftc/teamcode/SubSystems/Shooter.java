package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {

    private DcMotorEx shooterMotor;
    private DcMotor gateMotor;

    private static final double SHOOTER_VELOCITY = 1000;

    private static final double GATE_POWER = 0.6; // TODO: tune — how hard the gate motor drives

    public void init(HardwareMap hardwareMap) {
        shooterMotor = hardwareMap.get(DcMotorEx.class, "shooter");
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterMotor.setVelocity(0);

        gateMotor = hardwareMap.get(DcMotor.class, "shooterGate");
        gateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        closeGate();
    }

    public void shoot() {
        shooterMotor.setVelocity(SHOOTER_VELOCITY);
    }

    public void stop() {
        shooterMotor.setVelocity(0);
    }

    public void openGate() {
        gateMotor.setPower(GATE_POWER);
    }

    public void closeGate() {
        gateMotor.setPower(-GATE_POWER);
    }

    public double getCurrentVelocity() {
        return shooterMotor.getVelocity();

    }
}