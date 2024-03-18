
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.aim.aimAmpCMD;
import frc.robot.commands.aim.aimZeroCMD;
import frc.robot.commands.shooter.shooterBottomCMD;
import frc.robot.commands.shooter.shooterFeedCMD;
import frc.robot.commands.shooter.shooterTopCMD;
import frc.robot.subsystems.AimSub;
import frc.robot.subsystems.ShooterBottomSub;
import frc.robot.subsystems.ShooterFeedSub;
import frc.robot.subsystems.ShooterTopSub;

public class ShootAmp extends SequentialCommandGroup {
  public ShootAmp(AimSub aimSub,ShooterTopSub shooterTopSub, ShooterBottomSub shooterBottomSub, ShooterFeedSub shooterFeedSub) {
    super(
      (new aimAmpCMD(aimSub).withTimeout(0.5)
        .andThen(
          new shooterTopCMD(shooterTopSub, 0.4).withTimeout(0.6)
            .alongWith(new shooterBottomCMD(shooterBottomSub, 0.4)).withTimeout(0.6)
            .alongWith(new shooterFeedCMD(shooterFeedSub, 0.4)).withTimeout(0.6)
        )
      )
        .andThen(new aimZeroCMD(aimSub).withTimeout(0.5))
    );
  }
}
