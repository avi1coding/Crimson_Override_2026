package org.firstinspires.ftc.teamcode.OpModes.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red - Tipped")
public class AutoRedTipped extends AutoBase {

    // Leg 1: move out and around the tripod leg
    @Override
    protected double getUnparkForward() {
        return 0.5;
    }

    @Override
    protected double getUnparkStrafe() {
        return 0.5; // TODO: verify at the field — this direction needs to clear the pole, not drive into it
    }

    @Override
    protected double getUnparkSeconds() {
        return 1.0; // TODO: tune
    }

    // Leg 2: continue to the red square after clearing the pole
    @Override
    protected double getParkForward() {
        return 0.5;
    }

    @Override
    protected double getParkStrafe() {
        return 0.0; // TODO: tune
    }

    @Override
    protected double getParkSeconds() {
        return 1.0; // TODO: tune
    }

    @Override
    protected boolean shouldShootInAuto() {
        return false;
    }
}