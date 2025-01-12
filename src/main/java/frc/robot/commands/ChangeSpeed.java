
// Reapplies the offsets set by the preferences
// without having to deploy code.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TankSubsystem;

public class ChangeSpeed extends Command {
  /** Creates a new instance of ApplyOffsets. */
  private final TankSubsystem tankSubsystem;
  int amount;
  public ChangeSpeed(TankSubsystem tankSubsystem, int amount) {
    this.tankSubsystem = tankSubsystem;
    this.amount = amount;
    addRequirements(tankSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    tankSubsystem.speedChange(amount);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
