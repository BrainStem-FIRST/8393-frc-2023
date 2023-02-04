package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Tilt extends SubsystemBase {
  private static final class TiltConstants {
    private static final int solenoidID = 101; //FIXME

  }

  DoubleSolenoid tiltPneumatics;
  public Tilt() {
    //tiltPneumatics = new DoubleSolenoid(null, 0, 0);
  }

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

  public void setPosition(int Position){
    leftIntakeEncoder.setPosition(Position);
  }

  @Override
  public void periodic() {
  }

  @Override
  public void simulationPeriodic() {
  }
}

