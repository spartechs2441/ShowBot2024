// Copyright (c) FIRST and other WPILib contributors.

// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.autoCommands.JustRun;
import frc.robot.commands.autoCommands.ShootAndRunCenter;
import frc.robot.commands.autoCommands.StopCommands;
import frc.robot.commands.teleop.TeleopDriveCartesian;
import frc.robot.commands.teleop.feeder.FeederInward;
import frc.robot.commands.teleop.feeder.FeederOutward;
import frc.robot.commands.teleop.feeder.FeederStop;
import frc.robot.commands.teleop.flywheel.FlywheelsInward;
import frc.robot.commands.teleop.flywheel.FlywheelsOutward;
import frc.robot.commands.teleop.flywheel.FlywheelsStop;
import frc.robot.subsystems.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems and commands are defined here...
    private final DriveTrainSub driveSub;
    private final ShooterSub shooterSub;
    private final FeederSub feederSub;
    Joystick leftFlightStick = new Joystick(Constants.Port.SECONDARY_JOYSTICK);


    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {

        driveSub = new DriveTrainSub();
        shooterSub = new ShooterSub();
        feederSub = new FeederSub();
        configureBindings();
    }


    /**
     * Use this method to define your trigger->command mappings. Triggers can be created via the
     * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
     * predicate, or via the named factories in {@link
     * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
     * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
     * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
     * joysticks}.
     */
    private void configureBindings() {
        System.out.println(
                "Joystick name: " +
                leftFlightStick.getName()
        );

        driveSub.setDefaultCommand(
                new TeleopDriveCartesian(
                        driveSub,
                        leftFlightStick
                )
        );



        //Intake

        final JoystickButton feederIn = new JoystickButton(leftFlightStick, Constants.LeftButtons.FEEDER_IN);
        feederIn.onTrue(new FeederInward(feederSub));
        feederIn.onFalse(new FeederStop(feederSub));

        final JoystickButton feederOutButton = new JoystickButton(leftFlightStick, Constants.LeftButtons.FEEDER_OUT);
        feederOutButton.onTrue(new FeederOutward(feederSub));
        feederOutButton.onFalse(new FeederStop(feederSub));

        final JoystickButton shooterIn = new JoystickButton(leftFlightStick, Constants.LeftButtons.SLURP);
        shooterIn.onTrue(new FlywheelsInward(shooterSub));
        shooterIn.onFalse(new FlywheelsStop(shooterSub));

        final JoystickButton shootButton = new JoystickButton(leftFlightStick, Constants.LeftButtons.SPIT);
        shootButton.onTrue(new FlywheelsOutward(shooterSub));
        shootButton.onFalse(new FlywheelsStop(shooterSub));

//        final JoystickButton limelightMoving = new JoystickButton(rightFlightStick, Constants.RightButtons.LIMELIGHT);
//        limelightMoving.onTrue(new AprilTagTrackingMoving(driveSub));
//        limelightMoving.onFalse(new StopCommands());
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return new ShootAndRunCenter(shooterSub, feederSub, driveSub);
//       return new JustRun(driveSub);
    }
}
