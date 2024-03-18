
package frc.robot.commands.aim;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.AimSub;
import frc.robot.subsystems.LimeLight3;

public class aimDistanceCMD extends Command {
  private final AimSub neo;
  private final LimeLight3 apriltag;
  private static double speed;
  private static double _encoder;

  public aimDistanceCMD(AimSub _neo, LimeLight3 _apriltag) {
    this.neo = _neo;
    this.apriltag = _apriltag;
    addRequirements(neo, apriltag);
  }

  @Override
  public void initialize() {
    // 105-135 = 0
    // 295-305 = 630
  }

  @Override
  public void execute() {
    if (apriltag.tid == -1) {
      neo.stopNeo();
    }

    else if ((apriltag.distance > 285)) {
      _encoder = neo.encoder.getPosition() * 100;
      double a = ((105 - apriltag.distance) * (650 / 200));
      speed = (a - _encoder) * 0.0005;

      if (_encoder >= a) {
        neo.runNeo(speed);
      }

      else {
        neo.stopNeo();
      }
    }

    else if (apriltag.distance <= 285 && apriltag.distance > 270) {
      _encoder = neo.encoder.getPosition() * 100;
      double a = ((105 - apriltag.distance) * (785 / 200));
      speed = (a - _encoder) * 0.0005;

      if (_encoder >= a) {
        neo.runNeo(speed);
      }

      else {
        neo.stopNeo();
      }
    }

    else if (apriltag.distance <= 270 && apriltag.distance > 260) {
      _encoder = neo.encoder.getPosition() * 100;
      double a = ((105 - apriltag.distance) * (796 / 200));
      speed = (a - _encoder) * 0.0005;

      if (_encoder >= a) {
        neo.runNeo(speed);
      }

      else {
        neo.stopNeo();
      }
    }

    else if (apriltag.distance <= 260 && apriltag.distance > 250) {
      _encoder = neo.encoder.getPosition() * 100;
      double a = ((105 - apriltag.distance) * (786.25 / 200));
      speed = (a - _encoder) * 0.0005;

      if (_encoder >= a) {
        neo.runNeo(speed);
      }

      else {
        neo.stopNeo();
      }
    }

    else if (apriltag.distance <= 250 && apriltag.distance > 240) {
      _encoder = neo.encoder.getPosition() * 100;
      double a = ((105 - apriltag.distance) * (825 / 200));
      speed = (a - _encoder) * 0.0005;

      if (_encoder >= a) {
        neo.runNeo(speed);
      }

      else {
        neo.stopNeo();
      }
    }

    else if (apriltag.distance <= 240 && apriltag.distance > 220) {
      _encoder = neo.encoder.getPosition() * 100;
      double a = ((105 - apriltag.distance) * (875 / 200));
      speed = (a - _encoder) * 0.0005;

      if (_encoder >= a) {
        neo.runNeo(speed);
      }

      else {
        neo.stopNeo();
      }
    }

    else if (apriltag.distance <= 220 && apriltag.distance > 190) {
      _encoder = neo.encoder.getPosition() * 100;
      double a = ((105 - apriltag.distance) * (800 / 200));
      speed = (a - _encoder) * 0.0005;

      if (_encoder >= a) {
        neo.runNeo(speed);
      }

      else {
        neo.stopNeo();
      }
    }

    else if (apriltag.distance <= 190 && apriltag.distance > 135) {
      _encoder = neo.encoder.getPosition() * 100;
      double a = ((105 - apriltag.distance) * (800 / 200));
      speed = (a - _encoder) * 0.0005;

      if (_encoder >= a) {
        neo.runNeo(speed);
      }

      else {
        neo.stopNeo();
      }
    }

    else {
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
