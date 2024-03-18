
package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSub;

public class elevatorCMD extends Command {
  private final ElevatorSub neos;
  private final double speed;

  public elevatorCMD(ElevatorSub _neos, double _speed) {
    this.neos = _neos;
    this.speed = _speed;
    addRequirements(neos);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    neos.runNeo(speed);
  }

  @Override
  public void end(boolean interrupted) {
    neos.stopNeo();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
