package frc.robot;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Lift.Position;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver1 = new Joystick(0);
    private final Joystick driver2 = new Joystick(1);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;

  /* Driver Buttons */
  private final JoystickButton liftUp = new JoystickButton(driver1, XboxController.Button.kB.value);
  private final JoystickButton liftDown = new JoystickButton(driver1, XboxController.Button.kY.value);
  private final JoystickButton liftStop = new JoystickButton(driver1, XboxController.Button.kX.value);
  private final JoystickButton zeroGyro = new JoystickButton(driver1, XboxController.Button.kY.value);
  private final JoystickButton robotCentric = new JoystickButton(driver1, XboxController.Button.kLeftBumper.value);
  private final JoystickButton spinNeoMotor = new JoystickButton(driver1, XboxController.Button.kA.value);
    /* Subsystems */
  private final Swerve s_Swerve = new Swerve();
  private final Drivetrain drivetrain = new Drivetrain();
  private final Grabber grabber = new Grabber();
  private final Lift lift = new Lift();


  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    
    s_Swerve.setDefaultCommand(
        new TeleopSwerve(
            s_Swerve,
            () -> -driver1.getRawAxis(translationAxis) * 0.5,
            () -> -driver1.getRawAxis(strafeAxis) * 0.5,
            () -> -driver1.getRawAxis(rotationAxis) * 0.5,
            () -> robotCentric.getAsBoolean()));

    
            lift.initialize();

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /* Driver Buttons */
    liftUp.onTrue(new InstantCommand(() -> lift.state = Position.UP));
    liftDown.onTrue(new InstantCommand(() -> lift.state = Position.DOWN));
    liftStop.onTrue(new InstantCommand(() -> lift.state = Position.STOP));
    zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
    spinNeoMotor.onTrue(new InstantCommand(() -> grabber.collectorOn()));
    spinNeoMotor.onFalse(new InstantCommand(() -> grabber.collectorOff()));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
  
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return new InstantCommand(grabber::collectorOn);
    return new exampleAuto(s_Swerve);
  }
}
