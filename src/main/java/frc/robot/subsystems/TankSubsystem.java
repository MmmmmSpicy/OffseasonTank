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
import java.lang.Math;

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

        Preferences.initDouble("Speed Limit (Percent)", Constants.limitPercent);

    }

    public void driveTank(double leftSpeed, double rightSpeed) {
        robotDrive.tankDrive(-leftSpeed * (Math.sqrt((Preferences.getDouble("Speed Limit (Percent)", 100)) / 100.0)),
                            -rightSpeed * (Math.sqrt((Preferences.getDouble("Speed Limit (Percent)", 100)) / 100.0)));
    }

    public void driveArcade(double xSpeed, double rotSpeed) {
        robotDrive.arcadeDrive(-xSpeed * (Math.sqrt((Preferences.getDouble("Speed Limit (Percent)", 100)) / 100.0)),
                            -rotSpeed * (Math.sqrt((Preferences.getDouble("Speed Limit (Percent)", 100)) / 100.0)));
    }

    public void stop() {
        motorLeaderleft.stopMotor();
        motorLeaderright.stopMotor();
        motorFollowerleft.stopMotor();
        motorFollowerright.stopMotor();
    }

    public void speedChange(int amount) {
        if ((Preferences.getDouble("Speed Limit (Percent)", 100) + amount) > 100) {
            Preferences.setDouble("Speed Limit (Percent)", 100);
        }
        
        else if ((Preferences.getDouble("Speed Limit (Percent)", 100) + amount) < 0) {
            Preferences.setDouble("Speed Limit (Percent)", 0);
        }

        else {
            Preferences.setDouble("Speed Limit (Percent)", (Preferences.getDouble("Speed Limit (Percent)", 100) + amount));
        }
    }
    
    @Override
    public void periodic() {
        SmartDashboard.putNumber("Current Maximum Speed (Percent)", Constants.limitPercent);
        SendableRegistry.addChild(robotDrive, motorLeaderleft);
        SendableRegistry.addChild(robotDrive, motorLeaderright);
  }
}
