package frc.robot.autos;

import frc.robot.autoCommands.LiftCollectPreLoad;
import frc.robot.commands.BalanceCommand;
import frc.robot.commands.TeleopSwerve;
import frc.robot.subsystems.Extension;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.Swerve.SwerveConstants;

import java.time.Instant;
import java.util.List;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj.Timer;

public class autoCenter extends SequentialCommandGroup {


    public static final class AutoConstants { //TODO: The below constants are used in the example auto, and must be tuned to specific robot
        public static final double k_maxSpeedMetersPerSecond = 0.9;
        public static final double k_maxAccelerationMetersPerSecondSquared = 0.9;
        public static final double k_maxAngularSpeedRadiansPerSecond = Math.PI/10;
        public static final double k_maxAngularSpeedRadiansPerSecondSquared = Math.PI/10;
    
        public static final double k_pXController = 1;
        public static final double k_pYController = 0;
        public static final double k_pThetaController = 0;
    
        /* Constraint for the motion profilied robot angle controller */
        public static final TrapezoidProfile.Constraints k_thetaControllerConstraints =
            new TrapezoidProfile.Constraints(
                k_maxAngularSpeedRadiansPerSecond, k_maxAngularSpeedRadiansPerSecondSquared);
    }
    private Timer m_timer = new Timer();

    private static final class BalanceConstants {
        private static final double k_P = 0.03;
        private static final double k_I = 0.000;
        private static final double k_D = 0.001;
      }
    
    public autoCenter(Swerve s_Swerve){
        TrajectoryConfig config =
            new TrajectoryConfig(
                    AutoConstants.k_maxSpeedMetersPerSecond,
                    AutoConstants.k_maxAccelerationMetersPerSecondSquared)
                .setKinematics(SwerveConstants.k_swerveKinematics);

        config.setReversed(true);
        // An example trajectory to follow.  All units in meters.
        Trajectory runOverChargeStationTrajectory =
            TrajectoryGenerator.generateTrajectory(
                // Set the origin at (5,0) facing the +X direction
                // Robot starts facing the poles
                new Pose2d(5, 0, new Rotation2d(Math.toRadians(0))),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(
                    new Translation2d(2, 0) //Just kind of a test thing to see if another waypooint fixes auto
                ),
                // End 5 meters behind ahead of where we started, rotating 180 degrees, now facing forward
                new Pose2d(1, 0, new Rotation2d(Math.toRadians(0))),
                config);

                config.setReversed(false);
                // An example trajectory to follow.  All units in meters.
                Trajectory runBackOnToChargeStationTrajectory =
                    TrajectoryGenerator.generateTrajectory(
                        // Set the origin at (5,0) facing the +X direction
                        // Robot starts facing the poles
                        new Pose2d(1, 0, new Rotation2d(Math.toRadians(0))),
                        // Pass through these two interior waypoints, making an 's' curve path
                        List.of(
                            new Translation2d(2, 0) //Just kind of a test thing to see if another waypooint fixes auto
                        ),
                        // End 5 meters behind ahead of where we started, rotating 180 degrees, now facing forward
                        new Pose2d(3, 0, new Rotation2d(Math.toRadians(0))),
                        config);
                

        var thetaController =
            new ProfiledPIDController(
                AutoConstants.k_pThetaController * 2, 0, 0, AutoConstants.k_thetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        SwerveControllerCommand runOverChargeStationCommand =
            new SwerveControllerCommand(
                runOverChargeStationTrajectory,
                s_Swerve::getPose,
                SwerveConstants.k_swerveKinematics,
                new PIDController(AutoConstants.k_pXController, 0, 0),
                new PIDController(AutoConstants.k_pYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);

        SwerveControllerCommand runBackOntoChargeStationCommand =
                new SwerveControllerCommand(
                    runBackOnToChargeStationTrajectory,
                    s_Swerve::getPose,
                    SwerveConstants.k_swerveKinematics,
                    new PIDController(AutoConstants.k_pXController, 0, 0),
                    new PIDController(AutoConstants.k_pYController, 0, 0),
                    thetaController,
                    s_Swerve::setModuleStates,
                    s_Swerve);

        DriveOverChargeStationCommand driveOverChargeStationCommand = new DriveOverChargeStationCommand(s_Swerve);

        DriveBackOntoChargeStationCommand driveBackOntoChargeStationCommand = new DriveBackOntoChargeStationCommand(s_Swerve);


        addCommands(
            // new InstantCommand(() -> s_Swerve.setGyroRobotFacingReverse()),
            // collect pre load command here 

            // score on low pole command here 
            new InstantCommand(() -> m_timer.reset()),
            new InstantCommand(() -> m_timer.start()),
            new InstantCommand(() -> s_Swerve.resetOdometry(runOverChargeStationTrajectory.getInitialPose())), 
            runOverChargeStationCommand, 
            runBackOntoChargeStationCommand,
            new InstantCommand(() -> autoBalance(s_Swerve))

        );
    }

    private void autoBalance(Swerve s_Swerve) {
        double pitch = s_Swerve.getTilt();
        PIDController m_balancePID = new PIDController(BalanceConstants.k_P, BalanceConstants.k_I, BalanceConstants.k_D);
        m_balancePID.setTolerance(5);
        int counter = 0;
        while (s_Swerve.getTilt() > 10 || s_Swerve.getTilt() < -10) {
            pitch = s_Swerve.getTilt();
            double power = MathUtil.clamp(m_balancePID.calculate(pitch, 0), -0.25, 0.25);
        
            TeleopSwerve m_teleopSwerve = new TeleopSwerve(
                s_Swerve,
                () -> -power,
                () -> 0,
                () -> 0,
                () -> false
            );

            m_teleopSwerve.execute();
            counter ++;
            SmartDashboard.putNumber("Auto Pitch", pitch);
            SmartDashboard.putNumber("Balance Power", power);
            SmartDashboard.putNumber("Counter", counter);
        }
        TeleopSwerve m_teleopSwerve = new TeleopSwerve(
                s_Swerve,
                () -> 0,
                () -> 0,
                () -> 0,
                () -> false
            );
            m_teleopSwerve.execute();
    }
}