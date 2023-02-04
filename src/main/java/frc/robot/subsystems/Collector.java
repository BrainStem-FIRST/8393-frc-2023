package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Collector extends SubsystemBase {
  private static final class CollectorConstants {
    private static final int clawMotorID = 26; //FIXME
    private static final int wheelMotorID = 71; //FIXME
    private static final double wheelMotorSpeed = 1; //FIXME
  }
  CANSparkMax clawMotor;
  CANSparkMax wheelMotor;
  RelativeEncoder clawMotorEncoder;

  public Collector() {
    clawMotor = new CANSparkMax(CollectorConstants.clawMotorID, MotorType.kBrushless);
    clawMotorEncoder = clawMotor.getEncoder();
    wheelMotor = new CANSparkMax(CollectorConstants.wheelMotorID, MotorType.kBrushless);
  }

  public CommandBase exampleMethodCommand() {
    return runOnce(
        () -> {

        });
  }

  private void collectorOn() {
    wheelMotor.set(CollectorConstants.wheelMotorSpeed);
  }

  private void collectorOff() {
    wheelMotor.stopMotor();
  }

  private void openCollector() {

  }

  private void closeCollector() {

  }

  public void runCollector(boolean collectorOn, boolean closed) {
    if(collectorOn) {
      collectorOn();
    } else {
      collectorOff();
    }
    if(closed) {
      closeCollector();
    } else {
      openCollector();
    }
  }

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
  
  }
}

