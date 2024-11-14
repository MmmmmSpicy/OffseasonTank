// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final CANSparkMax Leaderleftmotor = new CANSparkMax(2, MotorType.kBrushless);
  private final CANSparkMax Leaderrightmotor = new CANSparkMax(4, MotorType.kBrushless);
  private final CANSparkMax Followerleftmotor = new CANSparkMax(1, MotorType.kBrushless);
  private final CANSparkMax Followerrightmotor = new CANSparkMax(3, MotorType.kBrushless);
  
  private final DifferentialDrive robotDrive =
      new DifferentialDrive(Leaderleftmotor::set, Leaderrightmotor::set);
  private final Joystick stick = new Joystick(0);

  public Robot() {
    SendableRegistry.addChild(robotDrive, Leaderleftmotor);
    SendableRegistry.addChild(robotDrive, Leaderrightmotor);
  }

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    Leaderrightmotor.setInverted(true);
    Followerrightmotor.setInverted(true);
    Followerleftmotor.follow(Leaderleftmotor);
    Followerrightmotor.follow(Leaderrightmotor);
    Leaderleftmotor.setIdleMode(IdleMode.kBrake);
    Leaderrightmotor.setIdleMode(IdleMode.kBrake);
    Followerleftmotor.setIdleMode(IdleMode.kBrake);
    Followerrightmotor.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    robotDrive.tankDrive(-stick.getRawAxis(1) / 1.0, -stick.getRawAxis(5) / 1.0);
  }
}
