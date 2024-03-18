
package frc.robot.commands.aim;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AimSub;
import frc.robot.subsystems.LimeLight3;
import frc.robot.Constants;

public class aimLL3CMD extends Command {
  private final AimSub neo;
  private final LimeLight3 apriltag;
  private static double speed;
  private static double _encoder;

  public aimLL3CMD(AimSub _neo, LimeLight3 _apriltag) {
    this.neo = _neo;
    this.apriltag = _apriltag;
    addRequirements(neo, apriltag);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    _encoder = neo.encoder.getPosition()*100;
    double a = getAngle();
    speed = (a - _encoder) * 0.0006;
    SmartDashboard.putNumber("a", a);
    SmartDashboard.putNumber("speed", speed);
    SmartDashboard.putNumber("encoder", _encoder);

    if (_encoder >= a) {
      neo.runNeo(speed);
    }

    else {
      neo.stopNeo();
    }
  }

  private double getAngle() {
    double closeDistance = Constants.Vision.kHoodMap.floorKey(apriltag.distance);
    double farDistance = Constants.Vision.kHoodMap.ceilingKey(apriltag.distance);
    double closeAim = Constants.Vision.kHoodMap.floorEntry(apriltag.distance).getValue();
    double farAim = Constants.Vision.kHoodMap.ceilingEntry(apriltag.distance).getValue();
    return ((farAim - closeAim) / (farDistance - closeDistance)) * (apriltag.distance - farDistance) + farAim;
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
