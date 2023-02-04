package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.BrainSTEMSubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Lift extends SubsystemBase implements BrainSTEMSubsystem{

  private static final class LiftConstants {
    private static final int liftMotorLeftID = 96; //FIXME
    private static final int liftMotorRightID = 100; //FIXME
    // private static final double liftProportional = 0.1;
    // private static final double liftIntegral = 0.1;
    // private static final double liftDerivative = 0.1;
  }

  TalonFX liftMotorLeft;
  TalonFX liftMotorRight;

  //PIDController liftPIDController;
  

  public Lift() {
    liftMotorLeft = new TalonFX(LiftConstants.liftMotorLeftID);
    liftMotorLeft = new TalonFX(LiftConstants.liftMotorRightID);
    //liftPIDController = new PIDController(LiftConstants.liftProportional, LiftConstants.liftIntegral, LiftConstants.liftDerivative);
    
  }

  public CommandBase exampleMethodCommand() {
    return runOnce(
        () -> {

        });
  }

  @Override
  public void reset() {

  }

  public void runToPosition(int position) {
    liftMotorLeft.set(ControlMode.Position, position);
    liftMotorRight.follow(liftMotorLeft);
  }

 
  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {

  }
}

