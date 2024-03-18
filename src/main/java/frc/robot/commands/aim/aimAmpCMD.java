
package frc.robot.commands.aim;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AimSub;
import frc.robot.Constants.Values;

public class aimAmpCMD extends Command {
  private final AimSub neo;
  private static double speed;
  private static double _encoder;

  public aimAmpCMD(AimSub _neo) {
    this.neo = _neo;
    addRequirements(neo);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    _encoder = neo.encoder.getPosition();
    speed = (Values.aimDistFW - _encoder) * Values.aimKP;
    if (_encoder >= Values.aimDistFW) {
      neo.runNeo(speed);
    } else {
      neo.stopNeo();
    }
  }

  @Override
  public void end(boolean interrupted) {
    neo.breakeFW();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
