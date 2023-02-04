package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Extension extends SubsystemBase {
  public static final class ExtensionConstants {

  }
  
  TalonFX extensionMotor;
  public Extension() {
    extensionMotor = new TalonFX(0, getName());
  }

  public CommandBase exampleMethodCommand() {
    return runOnce(
        () -> {

        });
  }



  @Override
  public void periodic() {

  }

  @Override
  public void simulationPeriodic() {

  }
}

