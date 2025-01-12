// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final int idLeaderleft = 2;
  private final int idLeaderright = 4;
  private final int idFollowerleft = 1;
  private final int idFollowerright = 3;

  private final boolean rightInverted = true;
  private final boolean leftInverted = false;

  private final SparkMax motorLeaderleft = new SparkMax(idLeaderleft, MotorType.kBrushless);
  private final SparkMax motorLeaderright = new SparkMax(idLeaderright, MotorType.kBrushless);
  private final SparkMax motorFollowerleft = new SparkMax(idFollowerleft, MotorType.kBrushless);
  private final SparkMax motorFollowerright = new SparkMax(idFollowerright, MotorType.kBrushless);

  private final SparkMaxConfig configLeaderleft = new SparkMaxConfig();
  private final SparkMaxConfig configLeaderright = new SparkMaxConfig();
  private final SparkMaxConfig configFollowerleft = new SparkMaxConfig();
  private final SparkMaxConfig configFollowerright = new SparkMaxConfig();


  private final DifferentialDrive robotDrive =
      new DifferentialDrive(motorLeaderleft::set, motorLeaderright::set);
  private final Joystick stick = new Joystick(0);

  public Robot() {
    SendableRegistry.addChild(robotDrive, motorLeaderleft);
    SendableRegistry.addChild(robotDrive, motorLeaderright);
  }

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.

    configLeaderright.inverted(leftInverted);
    configFollowerright.inverted(rightInverted);
    
    configFollowerleft.follow(idLeaderleft);
    configFollowerright.follow(idLeaderright);
    
    configLeaderleft.idleMode(IdleMode.kBrake);
    configLeaderright.idleMode(IdleMode.kBrake);
    configFollowerleft.idleMode(IdleMode.kBrake);
    configFollowerright.idleMode(IdleMode.kBrake);

    motorLeaderleft.configure(configLeaderleft, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorLeaderright.configure(configLeaderright, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorFollowerleft.configure(configFollowerleft, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    motorFollowerright.configure(configFollowerright, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.
    robotDrive.tankDrive(-stick.getRawAxis(1) / 1.0, -stick.getRawAxis(5) / 1.0);
  }
}
