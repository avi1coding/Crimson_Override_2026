package org.firstinspires.ftc.teamcode.OpModes.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red - Not Tipped")
public class AutoRedNotTipped extends AutoBase {

    // "A little forward" before shooting
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
        return 0.4; // TODO: tune at the field
    }

    @Override
    protected boolean shouldShootInAuto() {
        return true;
    }

    // Leg 1: clear the pole
    @Override
    protected double getParkForward() {
        return 0.3;
    }

    @Override
    protected double getParkStrafe() {
        return 0.5; // TODO: verify at the field — must clear the pole, not drive into it
    }

    @Override
    protected double getParkSeconds() {
        return 0.6; // TODO: tune
    }

    // Leg 2: continue on to the red square
    @Override
    protected double getParkForward2() {
        return 0.5;
    }

    @Override
    protected double getParkStrafe2() {
        return 0.3; // TODO: tune
    }

    @Override
    protected double getParkSeconds2() {
        return 1.0; // TODO: tune
    }
}