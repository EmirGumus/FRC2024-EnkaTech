
package frc.robot.commands.aim;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AimSub;

public class aimZeroCMD extends Command {
  private final AimSub neo;
  private static double speed;
  private static double _encoder;

  public aimZeroCMD(AimSub _neo) {
    this.neo = _neo;
    addRequirements(neo);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    _encoder = neo.encoder.getPosition();
    _encoder *= -100;
    speed = (_encoder * 0.0005);

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
