package org.firstinspires.ftc.teamcode.OpModes.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red - Tipped")
public class AutoRedTipped extends AutoBase {

    @Override
    protected double getUnparkPower() { return 0.5; } // TODO: tune

    @Override
    protected double getUnparkSeconds() { return 1.0; } // TODO: tune

    @Override
    protected double getParkPower() { return 0.5; } // TODO: tune

    @Override
    protected double getParkSeconds() { return 1.0; } // TODO: tune

    @Override
    protected boolean shouldShootInAuto() { return false; } // tipped robot just leaves + parks
}