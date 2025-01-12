package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import frc.robot.subsystems.*;
import frc.robot.commands.*;


public class RobotContainer {
 
    /*
    "Imports" subsystems that you make in the subsystem folder to be used for controller actions.
    Make sure you actually import the subsystem in the same manner as we do with the TankSubsystem above.
    */
    private final TankSubsystem tankSubsystem = new TankSubsystem();

    // Sets the Joystick/Physical Driver Station ports, change port order in Driver Station to the numbers below.
    private final Joystick driverJoystick = new Joystick(Constants.joystickPort); // 0

    /*
    Assigns raw inputs on whichever joystick you're using into buttons we use to control the robot.
    Feel free to change the names if you decide to change the controller to a non-PS4 controller for clarity sake.
    Check Driver Station for buttonNumbers, they'll be in the USB order tab.
    */
    Trigger X1 = new JoystickButton(driverJoystick, 1);
    Trigger O2 = new JoystickButton(driverJoystick, 2);
    Trigger Square3 = new JoystickButton(driverJoystick, 3);
    Trigger Triangle4 = new JoystickButton(driverJoystick, 4);
    Trigger leftShoulder5 = new JoystickButton(driverJoystick, 5);
    Trigger rightShoulder6 = new JoystickButton(driverJoystick, 6);
    Trigger leftTrigger7 = new JoystickButton(driverJoystick, 7);
    Trigger rightTrigger8 = new JoystickButton(driverJoystick, 8);
    Trigger leftStickPress9 = new JoystickButton(driverJoystick, 9);
    Trigger rightStickPress10 = new JoystickButton(driverJoystick, 10);
    
    Trigger dPadNorth = new POVButton(driverJoystick, 0);
    Trigger dPadSouth = new POVButton(driverJoystick, 180);
    Trigger dPadWest = new POVButton(driverJoystick, 270);
    Trigger dPadEast = new POVButton(driverJoystick, 90);



    public RobotContainer() {
        tankSubsystem.setDefaultCommand(new DriveTank( // Change DriveTank to DriveArcade for Arcade Drive!!!!!
                tankSubsystem,
                /* 
                In teleop, if the robot is moving opposite of the way the joystick is being moved, change one of these
                negatives to a positive depending on how it's inverted.
                */
                () -> -driverJoystick.getRawAxis(Constants.leftforwardAxis),
                () -> -driverJoystick.getRawAxis(Constants.rightrotAxis)
                ));

        configureButtonBindings();

  }
  

    private void configureButtonBindings() {
        /* 
        Used to set all Button Bindings as the name suggests, excluding moving the robot with the joystick,
        which is set with the Command Scheduler.
        */

        // X1.onTrue(new ResetHeading(swerveSubsystem));
        // O2.onTrue(new ResyncEncoders(swerveSubsystem)); 
        // Square3.onTrue(new ApplyOffsets(swerveSubsystem));
        // Triangle4.onTrue(new);
        // leftShoulder5.onTrue(new);
        // rightShoulder6.onTrue(new);
        X1.onTrue(new ChangeSpeed(tankSubsystem, -10));
        O2.onTrue(new ChangeSpeed(tankSubsystem, 10));
        // leftStickPress9.onTrue(new);
        // rightStickPress10.onTrue(new);
        // dPadNorth.onTrue(new);
        // dPadEast.onTrue(new);
        // dPadSouth.onTrue(new);
        // dPadWest.onTrue(new);

        // buttonT1.onTrue(new);
        // buttonT2.onTrue(new); 
        // buttonT3.onTrue(new);
        // buttonT4.onTrue(new);
        // buttonT5.onTrue(new);
        // buttonT6.onTrue(new);
        // buttonT7.onTrue(new);
        // buttonT8.onTrue(new);
        // buttonT9.onTrue(new);
        // buttonT10.onTrue(new);

        // buttonB1.onTrue(new);
        // buttonB2.onTrue(new);
        // buttonB3.onTrue(new);
        // buttonB4.onTrue(new);
        // buttonB5.onTrue(new);
        // buttonB6.onTrue(new);
        // buttonB6.onTrue(new);
        // buttonB7.onTrue(new);
        // buttonB8.onTrue(new);
        // buttonB8.onTrue(new);
        // buttonB9.onTrue(new);
        // buttonB10.onTrue(new);

        // // buttonD7.onTrue(new);
        // // buttonD8.onTrue(new);
        // // buttonD9.onTrue(new);
    }

    public Command getAutonomousCommand() {
            return null;
  }
    
}
