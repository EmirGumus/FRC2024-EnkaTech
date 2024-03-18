
package frc.robot.commands.aim;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AimSub;

public class aimCMD extends Command {
  private final AimSub neo;
  private double speed;

  public aimCMD(AimSub _neo, double _speed) {
    this.neo = _neo;
    this.speed = _speed;
    addRequirements(neo);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    neo.runNeo(speed);
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
