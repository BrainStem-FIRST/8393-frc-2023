package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Tilt extends SubsystemBase {
  public Tilt() {}

  public CommandBase exampleMethodCommand() {
    return runOnce(
        () -> {

        });
  }


  public CANSparkMax leftIntake = new CANSparkMax(26, MotorType.kBrushless);
  private RelativeEncoder leftIntakeEncoder = leftIntake.getEncoder();
  public boolean exampleCondition() {
    return false;
  }

  public void collectorOn() {
    leftIntake.set(0.1);
  }

  public void collectorOff() {
    leftIntake.set(0.0);
  }

  public void setPosition(int Position){
    leftIntakeEncoder.setPosition(Position);
  }

  @Override
  public void periodic() {
    // SmartDashboard.putNumber("Grabber Velocity", leftIntakeEncoder.getVelocity());
    // SmartDashboard.putNumber("Grabber Current", leftIntake.getOutputCurrent());
    SmartDashboard.putNumber("Motor Encoder Value", leftIntakeEncoder.getPosition());
  }

  @Override
  public void simulationPeriodic() {

  }
}

