package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Auto Blue Left", group = "BIOBUZZ")
public class AutoBlueLeft extends AutoBase {
    @Override
    public void runOpMode() {
        alliance = Alliance.BLUE;
        startSide = StartSide.LEFT;

        initHardware();
        waitForStart();

        if (opModeIsActive()) {
            runAuto();
        }
    }
}