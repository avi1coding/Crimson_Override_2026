package org.firstinspires.ftc.teamcode.OpModes.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue - Tipped")
public class AutoBlueTipped extends AutoBase {

    @Override
    protected double getUnparkForward() { return 0.5; } // TODO: tune

    @Override
    protected double getUnparkStrafe() { return 0.5; } // mirrored sign from red — toward blue garden

    @Override
    protected double getUnparkSeconds() { return 1.0; } // TODO: tune

    @Override
    protected double getParkForward() { return 0.5; } // TODO: tune

    @Override
    protected double getParkStrafe() { return 0.5; } // mirrored sign from red

    @Override
    protected double getParkSeconds() { return 1.0; } // TODO: tune

    @Override
    protected boolean shouldShootInAuto() { return false; }
}