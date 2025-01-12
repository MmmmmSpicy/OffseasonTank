package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.util.sendable.SendableRegistry;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class TankSubsystem extends SubsystemBase {
    private final SparkMax motorLeaderleft = new SparkMax(Constants.idLeaderleft, MotorType.kBrushless);
    private final SparkMax motorLeaderright = new SparkMax(Constants.idLeaderright, MotorType.kBrushless);
    private final SparkMax motorFollowerleft = new SparkMax(Constants.idFollowerleft, MotorType.kBrushless);
    private final SparkMax motorFollowerright = new SparkMax(Constants.idFollowerright, MotorType.kBrushless);

    private final SparkMaxConfig configLeaderleft = new SparkMaxConfig();
    private final SparkMaxConfig configLeaderright = new SparkMaxConfig();
    private final SparkMaxConfig configFollowerleft = new SparkMaxConfig();
    private final SparkMaxConfig configFollowerright = new SparkMaxConfig();

    private final DifferentialDrive robotDrive =
      new DifferentialDrive(motorLeaderleft::set, motorLeaderright::set);

    public TankSubsystem() {
        configLeaderright.inverted(Constants.leftInverted);
        configFollowerright.inverted(Constants.rightInverted);
        
        configFollowerleft.follow(Constants.idLeaderleft);
        configFollowerright.follow(Constants.idLeaderright);
        
        configLeaderleft.idleMode(IdleMode.kBrake);
        configLeaderright.idleMode(IdleMode.kBrake);
        configFollowerleft.idleMode(IdleMode.kBrake);
        configFollowerright.idleMode(IdleMode.kBrake);

        motorLeaderleft.configure(configLeaderleft, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorLeaderright.configure(configLeaderright, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorFollowerleft.configure(configFollowerleft, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        motorFollowerright.configure(configFollowerright, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

        Preferences.initInt("Speed Limit (Percent)", Constants.limitPercent);

    }

    public void driveTank(double leftSpeed, double rightSpeed) {
        robotDrive.tankDrive(-leftSpeed * (Constants.limitPercent/100), -rightSpeed * (Constants.limitPercent/100));
    }

    public void driveArcade(double xSpeed, double rotSpeed) {
        robotDrive.arcadeDrive(-xSpeed * (Constants.limitPercent/100), -rotSpeed * (Constants.limitPercent/100));
    }

    public void stop() {
        motorLeaderleft.stopMotor();
        motorLeaderright.stopMotor();
        motorFollowerleft.stopMotor();
        motorFollowerright.stopMotor();
    }

    public void speedChange(int amount) {
        if (Preferences.getInt("Speed Limit (Percent)", 100) + amount > 100) {
            Preferences.setInt("Speed Limit (Percent)", 100);
        }
        
        else if (Preferences.getInt("Speed Limit (Percent)", 100) + amount < 0) {
            Preferences.setInt("Speed Limit (Percent)", 0);
        }

        else {
            Preferences.setInt("Speed Limit (Percent)", amount);
        }
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Current Maximum Speed (Percent)", Constants.limitPercent);
        SendableRegistry.addChild(robotDrive, motorLeaderleft);
        SendableRegistry.addChild(robotDrive, motorLeaderright);
  }
}
