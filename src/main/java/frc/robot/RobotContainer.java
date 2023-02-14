package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import frc.robot.subsystems.Lift.Position;


public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);

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
            () -> -driver1.getRawAxis(translationAxis),
            () -> -driver1.getRawAxis(strafeAxis),
            () -> -driver1.getRawAxis(rotationAxis),
            () -> robotCentric.getAsBoolean()));

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
        return new exampleAuto(s_Swerve);
    }
}
