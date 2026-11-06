package org.firstinspires.ftc.teamcode.SubSystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {

    private DcMotor intakeMotor;
    private CRServo intakeServoLeft;
    private CRServo intakeServoRight;

    public static final double INTAKE_POWER = 0.7;
    public static final double OUTTAKE_POWER = -0.8;
    public static final double SERVO_POWER = 1.0;

    public Intake(HardwareMap hardwareMap) {
        intakeMotor = hardwareMap.get(DcMotor.class, "intakeMotor");
        intakeMotor.setDirection(DcMotor.Direction.REVERSE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        intakeServoLeft = hardwareMap.get(CRServo.class, "intakeServoLeft");
        intakeServoRight = hardwareMap.get(CRServo.class, "intakeServoRight");

        // Servos face each other, so one has to be reversed
        // to make both feed inward
        intakeServoLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        intakeServoRight.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void intake() {
        intakeMotor.setPower(INTAKE_POWER);
        intakeServoLeft.setPower(-SERVO_POWER);
        intakeServoRight.setPower(-SERVO_POWER);
    }

    public void outtake() {
        intakeMotor.setPower(OUTTAKE_POWER);
        intakeServoLeft.setPower(SERVO_POWER);
        intakeServoRight.setPower(SERVO_POWER);
    }

    public void stop() {
        intakeMotor.setPower(0);
        intakeServoLeft.setPower(0);
        intakeServoRight.setPower(0);
    }
}