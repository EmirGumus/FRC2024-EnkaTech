
package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.Constants.Values;

public class elevatorPIDUpCMD extends Command {
  private final ElevatorSub neo;
  private static double speed;
  private static double _encoder;

  public elevatorPIDUpCMD(ElevatorSub _neo) {
    this.neo = _neo;
    addRequirements(neo);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    _encoder = neo.encoder.getPosition();
    speed = (Values.elevatorDist - _encoder) * Values.elevatorKP;
    
    if (_encoder >= Values.elevatorDist) {
      neo.runNeo(speed);
    } else {
      neo.stopNeo();
    }
  }

  @Override
  public void end(boolean interrupted) {
    neo.breakeNeo();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
