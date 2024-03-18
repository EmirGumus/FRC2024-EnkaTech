
package frc.robot.commands.elevator;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Values;
import frc.robot.subsystems.ElevatorSub;

public class elevatorPIDDownCMD extends Command {
  private final ElevatorSub neo;
  private static double speed;
  private static double _encoder;

  public elevatorPIDDownCMD(ElevatorSub _neo) {
    this.neo = _neo;
    addRequirements(neo);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    _encoder = neo.encoder.getPosition();
    _encoder *= -1;
    speed = (_encoder * Values.elevatorKP);
    
    if (_encoder <= 0) {
      neo.stopNeo();
    } else {
      neo.runNeo(speed);
    }
  }

  @Override
  public void end(boolean interrupted) {
    neo.stopNeo();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
