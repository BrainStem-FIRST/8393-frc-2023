package frc.robot.commands;

import frc.robot.subsystems.Collector;
import frc.robot.subsystems.Extension;
import frc.robot.subsystems.Lift;
import edu.wpi.first.wpilibj2.command.CommandBase;


public class DepositCommand extends CommandBase {   
    private static final class DepositCommandConstants {

    }

    private Collector collector;    
    private Extension extension;
    private Lift lift;

    public DepositCommand(Collector collector, Extension extension, Lift lift) {
        this.collector = collector;
        this.extension = extension;
        this.lift = lift;
    }

    @Override
    public void execute() {
        
    }
}