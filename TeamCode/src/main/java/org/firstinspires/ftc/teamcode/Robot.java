package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Hardware.RobotMap;
import org.firstinspires.ftc.teamcode.SubSystems.Intake;
import org.firstinspires.ftc.teamcode.SubSystems.Shooter;

public class Robot {

    public RobotMap drivetrain;
    public Intake intake;
    public Shooter shooter;

    public void init(HardwareMap hardwareMap) {
        drivetrain = new RobotMap();
        drivetrain.init(hardwareMap);

        intake = new Intake(hardwareMap);

        shooter = new Shooter();
        shooter.init(hardwareMap);
    }
}