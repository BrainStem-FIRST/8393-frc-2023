package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Extension extends SubsystemBase {
  public Extension() {}

  public CommandBase exampleMethodCommand() {
    return runOnce(
        () -> {

        });
  }


  Servo ratchet = new Servo(0);
  public boolean exampleCondition() {
    return false;
  }

  public void ratchetLock() {
    ratchet.set(0.1);
  }

  public void ratchetUnlock() {
    ratchet.set(0.9);
  }

  @Override
  public void periodic() {
    // SmartDashboard.putNumber("Grabber Velocity", leftIntakeEncoder.getVelocity());
    // SmartDashboard.putNumber("Grabber Current", leftIntake.getOutputCurrent());
  }

  @Override
  public void simulationPeriodic() {

  }
}

