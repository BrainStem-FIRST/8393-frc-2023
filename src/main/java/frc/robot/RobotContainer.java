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
    private final JoystickButton ratchetLock = new JoystickButton(driver, XboxController.Button.kRightStick.value);
    private final JoystickButton ratchetUnlock = new JoystickButton(driver, XboxController.Button.kLeftStick.value);
    private final JoystickButton liftUp = new JoystickButton(driver, XboxController.Button.kB.value);
    private final JoystickButton liftDown = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton liftStop = new JoystickButton(driver, XboxController.Button.kX.value);
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kStart.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    private final JoystickButton testEncoderMotor = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
    private final JoystickButton testEncoderMotor2 = new JoystickButton(driver, XboxController.Button.kRightBumper.value);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();
    private final Grabber grabber = new Grabber();
    private final Lift lift = new Lift();
    private final Extension extension = new Extension();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis) * 0.25, 
                () -> -driver.getRawAxis(strafeAxis) * 0.25, 
                () -> -driver.getRawAxis(rotationAxis) * 0.25, 
                () -> robotCentric.getAsBoolean()
            )
        );


        lift.initialize();

        // Configure the button bindings
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        /* Driver Buttons */
        ratchetLock.onTrue(new InstantCommand(() -> extension.ratchetLock()));
        ratchetUnlock.onTrue(new InstantCommand(() -> extension.ratchetUnlock()));
        liftUp.onTrue(new InstantCommand(() -> lift.state = Position.UP));
        liftDown.onTrue(new InstantCommand(() -> lift.state = Position.DOWN));
        liftStop.onTrue(new InstantCommand(() -> lift.state = Position.STOP));
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroGyro()));
        testEncoderMotor.onTrue(new InstantCommand(() -> grabber.collectorOn()));
        testEncoderMotor2.onTrue(new InstantCommand(() -> grabber.collectorOff()));
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
