package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Lift extends SubsystemBase {

  private static final class LiftConstants {
    private static final int liftMotorLeftID = 96; //FIXME
    private static final int liftMotorRightID = 100; //FIXME
  }

  TalonFX liftMotorLeft;
  TalonFX liftMotorRight;

  public Lift() {
    liftMotorLeft = new TalonFX(LiftConstants.liftMotorLeftID);
    liftMotorLeft = new TalonFX(LiftConstants.liftMotorRightID);
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

