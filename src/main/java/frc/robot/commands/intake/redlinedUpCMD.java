
package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RotationSub;

public class redlinedUpCMD extends Command {
  private final RotationSub redline;
  private static double speed;
  private static double _encoder;

  public redlinedUpCMD(RotationSub _redline) {
    this.redline = _redline;
    addRequirements(redline);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    _encoder = redline.intakeEncoder1.getPosition();
    _encoder *= -1;
    speed = _encoder * 1.25; //* Values.intakeKP); // 0.175

    if (_encoder <= 0.05) { // 0.005
      redline.stopRedline();
    } else {
      redline.runRedline(speed);
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
