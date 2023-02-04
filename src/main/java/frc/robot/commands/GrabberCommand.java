package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Collector;

public class GrabberCommand extends CommandBase{
    
    private Collector grabber;
    
    @Override
    public void execute() {
        grabber.setPosition(500);
    }
}
