
package frc.robot.commands.aim;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AimSub;
import frc.robot.Constants.Values;

public class aimFloorCMD extends Command {
  private final AimSub neo;
  private static double speed;
  private static double _encoder;

  public aimFloorCMD(AimSub _neo) {
    this.neo = _neo;
    addRequirements(neo);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    _encoder = neo.encoder.getPosition();
    speed = (-14 - _encoder) * Values.aimKP;
    if (_encoder >= -14) {
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
