
package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterTopSub;

public class shooterTopCMD extends Command {
  private final ShooterTopSub neo;
  private final double speed;

  public shooterTopCMD(ShooterTopSub _neo, double _speed) {
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
