
package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSub;

public class intakeSparkMaxCMD extends Command {
  private final IntakeSub neo;
  private final double speed;

  public intakeSparkMaxCMD(IntakeSub _neo, double _speed) {
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
