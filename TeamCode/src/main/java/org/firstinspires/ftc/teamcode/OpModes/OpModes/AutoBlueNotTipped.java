package org.firstinspires.ftc.teamcode.OpModes.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue - Not Tipped")
public class AutoBlueNotTipped extends AutoBase {

    // Short move off the wall before shooting — you said this is minor
    @Override
    protected double getUnparkForward() {
        return 0.3;
    }

    @Override
    protected double getUnparkStrafe() {
        return 0.0;
    }

    @Override
    protected double getUnparkSeconds() {
        return 0.4; // TODO: tune at the field — just enough to clear the wall
    }

    @Override
    protected boolean shouldShootInAuto() {
        return true;
    }

    // First park leg: clear the tripod leg before heading toward the garden
    @Override
    protected double getParkForward() {
        return 0.0;
    }

    @Override
    protected double getParkStrafe() {
        return 0.5; // TODO: verify this direction actually clears the pole, not toward it
    }

    @Override
    protected double getParkSeconds() {
        return 0.6; // TODO: tune at the field
    }

    // Second park leg: continue on to the garden
    @Override
    protected double getParkForward2() {
        return 0.5;
    }

    @Override
    protected double getParkStrafe2() {
        return 0.0; // TODO: tune at the field
    }

    @Override
    protected double getParkSeconds2() {
        return 1.0; // TODO: tune at the field
    }
}