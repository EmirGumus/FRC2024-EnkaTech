
package frc.robot.commands.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShooterFeedSub;

public class shooterFeedCMD extends Command {
  private final ShooterFeedSub neo;
  private final double speed;

  public shooterFeedCMD(ShooterFeedSub _neo, double _speed) {
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
