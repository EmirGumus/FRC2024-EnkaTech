
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.aim.aimZeroCMD;
import frc.robot.commands.aim.aimLL3CMD;
import frc.robot.commands.shooter.shooterBottomCMD;
import frc.robot.commands.shooter.shooterFeedCMD;
import frc.robot.commands.shooter.shooterTopCMD;
import frc.robot.subsystems.AimSub;
import frc.robot.subsystems.LimeLight3;
import frc.robot.subsystems.ShooterBottomSub;
import frc.robot.subsystems.ShooterFeedSub;
import frc.robot.subsystems.ShooterTopSub;

public class kerem extends SequentialCommandGroup {
  /** Creates a new kerem. */
  public kerem(
      LimeLight3 limeLight3,
      ShooterBottomSub shooterBottomSub,
      ShooterTopSub shooterTopSub,
      AimSub aimSub,
      ShooterFeedSub shooterFeedSub) {
    addCommands( // 7-5-b-6

        (new ShootSpeaker(shooterTopSub, shooterBottomSub)
            .alongWith(
              new aimLL3CMD(aimSub, limeLight3).withTimeout(0.6)
              .andThen(
                new shooterFeedCMD(shooterFeedSub, 1).withTimeout(0.275)
              )
              .andThen(
                new aimZeroCMD(aimSub).withTimeout(1)
              )
            )
        )
          .andThen(new shooterTopCMD(shooterTopSub, 0).alongWith(new shooterBottomCMD(shooterBottomSub, 0)))
          .withTimeout(1.25)

    );
  }
}
