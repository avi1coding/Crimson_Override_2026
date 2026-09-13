package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.hardware.HardwareMap;
public class Shooter {
    private DcMotorEx shooterMotor;
    private final double SHOOTER_VELOCITY = 1800;
    public void init(HardwareMap hardwareMap) {
        shooterMotor = hardwareMap.get(DcMotorEx.class, "shooter");
        shooterMotor.setDirection(DcMotor.Direction.FORWARD);
    }
    public void shoot() {
        shooterMotor.setVelocity(SHOOTER_VELOCITY);
    }
        public void stop() {
            shooterMotor.setVelocity(0);
        }
        public double getCurrentVelocity() {
        return shooterMotor.getVelocity();
        }


    }


