
// Drives da robot whenever no other commands
// are using the TankSubsystem.

package frc.robot.commands;

import java.util.function.Supplier;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankSubsystem;

public class DriveArcade extends Command {

    private final TankSubsystem tankSubsystem;
    private final Supplier<Double> xSpeedSupplier, rotSpeedSupplier;

    public DriveArcade(TankSubsystem tankSubsystem, Supplier<Double> xSpeedSupplier,
                            Supplier<Double> rotSpeedSupplier) {
        
        this.tankSubsystem = tankSubsystem;
        this.xSpeedSupplier = xSpeedSupplier;
        this.rotSpeedSupplier = rotSpeedSupplier;
        addRequirements(tankSubsystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        double xSpeed = xSpeedSupplier.get();
        double rotSpeed = rotSpeedSupplier.get();
        tankSubsystem.driveTank(xSpeed, rotSpeed);
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
