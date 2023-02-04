package frc.robot.commands;

import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Tilt;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class DefaultTiltCommand extends CommandBase {   

    private Tilt tilt;    
    private DoubleSupplier tiltDistance;

    public DefaultTiltCommand(Tilt tilt, DoubleSupplier tiltDistance) {

        this.tilt = tilt;
        this.tiltDistance = tiltDistance;
        addRequirements(tilt);

    }
    @Override
    public void execute() {
    
    }
}