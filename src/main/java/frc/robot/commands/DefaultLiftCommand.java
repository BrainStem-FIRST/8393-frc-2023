package frc.robot.commands;

import frc.robot.subsystems.Lift;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DefaultLiftCommand extends CommandBase {   

    private Lift lift;    
    private DoubleSupplier liftDistance;

    public DefaultLiftCommand(Lift lift, DoubleSupplier liftDistance) {

        this.lift = lift;
        this.liftDistance = liftDistance;
        addRequirements(lift);

    }
    @Override
    public void execute() {
    }
}