
// Drives da robot whenever no other commands
// are using the TankSubsystem.

package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankSubsystem;

public class DriveTank extends Command {

    private final TankSubsystem tankSubsystem;
    private final Supplier<Double> leftSpeedSupplier, rightSpeedSupplier;

    public DriveTank(TankSubsystem tankSubsystem, Supplier<Double> leftSpeedSupplier,
                            Supplier<Double> rightSpeedSupplier) {
        
        this.tankSubsystem = tankSubsystem;
        this.leftSpeedSupplier = leftSpeedSupplier;
        this.rightSpeedSupplier = rightSpeedSupplier;
        addRequirements(tankSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double leftSpeed = leftSpeedSupplier.get();
        double rightSpeed = rightSpeedSupplier.get();
        tankSubsystem.driveTank(leftSpeed, rightSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        tankSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
