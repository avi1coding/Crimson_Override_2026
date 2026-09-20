package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.Hardware.RobotMap;

public abstract class AutoBase extends LinearOpMode {

    protected RobotMap robot;

    protected abstract void runPath();
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new RobotMap();
        robot.init(hardwareMap);
        waitForStart();
        runPath();
    }
}