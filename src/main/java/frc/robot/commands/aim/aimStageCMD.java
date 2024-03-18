
package frc.robot.commands.aim;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AimSub;

public class aimStageCMD extends Command {
  private final AimSub neo;
  private static double speed;
  private static double _encoder;

  public aimStageCMD(AimSub _neo) {
    this.neo = _neo;
    addRequirements(neo);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    _encoder = neo.encoder.getPosition();
    speed = (-42.5 - _encoder) * 0.02;

    if (_encoder >= -42.5) {
      neo.runNeo(speed);
    } else {
      neo.stopNeo();
    }
  }

  @Override
  public void end(boolean interrupted) {
    neo.breakeBW();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
