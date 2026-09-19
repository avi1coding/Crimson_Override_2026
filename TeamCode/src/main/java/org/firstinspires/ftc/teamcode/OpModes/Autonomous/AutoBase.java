package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Hardware.RobotMap;

public abstract class AutoBase extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // Example: drive = new Drivetrain(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        // This triggers the specific path in the files below
        runPath();
    }

    // Force every auto file to have a runPath method
    protected abstract void runPath() throws InterruptedException;
}
