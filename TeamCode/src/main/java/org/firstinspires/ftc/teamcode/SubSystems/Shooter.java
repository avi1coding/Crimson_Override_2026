package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {

    private DcMotorEx shooterMotor;
    private DcMotor gateMotor;

    private static final double SHOOTER_VELOCITY = 1440;
    private static final double GATE_POWER = 0.6; // TODO: tune

    public void init(HardwareMap hardwareMap) {
        shooterMotor = hardwareMap.get(DcMotorEx.class, "shooter");
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooterMotor.setVelocity(0);

        gateMotor = hardwareMap.get(DcMotor.class, "shooterGate");
        gateMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Nothing set here on purpose — gate stays still until you press A
    }

    public void shoot() {
        shooterMotor.setVelocity(SHOOTER_VELOCITY);
    }

    public void stop() {
        shooterMotor.setVelocity(0);
    }

    public void runGate() {
        gateMotor.setPower(GATE_POWER);
    }

    public void stopGate() {
        gateMotor.setPower(0);
    }

    public double getCurrentVelocity() {
        return shooterMotor.getVelocity();

    }
}