
package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RotationSub;

public class redlineCMD extends Command {
  private final RotationSub redline;
  private final double speed;

  public redlineCMD(RotationSub _redline, double _speed) {
    this.redline = _redline;
    this.speed = _speed;
    addRequirements(redline);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    redline.runRedline(speed);
  }

  @Override
  public void end(boolean interrupted) {
    redline.stopRedline();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
