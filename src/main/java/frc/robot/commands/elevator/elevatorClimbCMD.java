
package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSub;

public class elevatorClimbCMD extends Command {
  private final ElevatorSub neo;
  private static double speed;
  private static double _encoder;

  public elevatorClimbCMD(ElevatorSub _neo) {
    this.neo = _neo;
    addRequirements(neo);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    _encoder = neo.encoder.getPosition();
    speed = 1;
    if (_encoder >= 0) {
      neo.stopNeo();
    } else {
      neo.runNeo(speed);
    }
  }

  @Override
  public void end(boolean interrupted) {
    neo.climbNeo();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
