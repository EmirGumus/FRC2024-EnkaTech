
package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.Values;
import frc.robot.subsystems.RotationSub;

public class redlineDownCMD extends Command {
  private final RotationSub redline;
  private static double speed;
  private static double _encoder;

  public redlineDownCMD(RotationSub _redline) {
    this.redline = _redline;
    addRequirements(redline);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    _encoder = redline.intakeEncoder1.getPosition();
    speed = (Values.intakeDist - _encoder) * Values.intakeKP; // 0.25- *0.185

    if (_encoder >= Values.intakeDist) { // 0.245
      redline.runRedline(speed);
    } else {
      redline.stopRedline();
    }
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
