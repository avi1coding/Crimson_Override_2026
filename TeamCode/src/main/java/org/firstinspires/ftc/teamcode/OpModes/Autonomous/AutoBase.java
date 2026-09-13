package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public abstract class AutoBase extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        // PUT COMMON HARDWARE INITIALIZATION HERE
        // Example: drive = new Drivetrain(hardwareMap);

        waitForStart();
        if (isStopRequested()) return;

        // This triggers the specific path in the files below
        runPath();
    }

    // Force every auto file to have a runPath method
    protected abstract void runPath() throws InterruptedException;
}
