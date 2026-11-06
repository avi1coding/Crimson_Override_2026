package org.firstinspires.ftc.teamcode.OpModes.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Blue - Tipped")
public class AutoBlueTipped extends AutoBase {

    // Leg 1: forward a bit
    @Override
    protected double getUnparkForward() {
        return 0.5;
    }

    @Override
    protected double getUnparkStrafe() {
        return 0.0;
    }

    @Override
    protected double getUnparkSeconds() {
        return 0.6; // TODO: tune at the field — "forward a bit"
    }

    // Leg 2: to the blue square
    @Override
    protected double getParkForward() {
        return 0.5;
    }

    @Override
    protected double getParkStrafe() {
        return -0.3; // TODO: tune direction toward the blue square
    }

    @Override
    protected double getParkSeconds() {
        return 1.0; // TODO: tune at the field
    }

    @Override
    protected boolean shouldShootInAuto() {
        return false;
    }
}