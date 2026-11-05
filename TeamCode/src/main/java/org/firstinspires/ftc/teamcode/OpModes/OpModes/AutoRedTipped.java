package org.firstinspires.ftc.teamcode.OpModes.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "Red - Tipped")
public class AutoRedTipped extends AutoBase {

    // Straight diagonal line toward the red garden, from your video.
    // 0.5/-0.5 is a 45-degree placeholder since I couldn't read the exact
    // angle off the video — test it and adjust the ratio to match the real line.
    @Override
    protected double getUnparkForward() { return 0.5; } // TODO: tune

    @Override
    protected double getUnparkStrafe() { return -0.5; } // TODO: tune, sign = direction toward red garden

    @Override
    protected double getUnparkSeconds() { return 1.0; } // TODO: tune

    @Override
    protected double getParkForward() { return 0.5; } // TODO: tune (same diagonal, continues)

    @Override
    protected double getParkStrafe() { return -0.5; } // TODO: tune (same diagonal, continues)

    @Override
    protected double getParkSeconds() { return 1.0; } // TODO: tune

    @Override
    protected boolean shouldShootInAuto() { return false; } // tipped robot doesn't shoot
}