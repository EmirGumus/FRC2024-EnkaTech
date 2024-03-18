
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.shooter.shooterBottomCMD;
import frc.robot.commands.shooter.shooterTopCMD;
import frc.robot.subsystems.ShooterBottomSub;
import frc.robot.subsystems.ShooterTopSub;

public class ShootSpeaker extends SequentialCommandGroup {
  public ShootSpeaker(ShooterTopSub shooterTopSub, ShooterBottomSub shooterBottomSub) {
    super(
      new shooterTopCMD(shooterTopSub, 0.9).alongWith(new shooterBottomCMD(shooterBottomSub, 0.7))
    );
  }
}
