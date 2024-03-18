
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.aim.aimFloorCMD;
import frc.robot.commands.aim.aimZeroCMD;
import frc.robot.commands.shooter.shooterBottomCMD;
import frc.robot.commands.shooter.shooterFeedCMD;
import frc.robot.commands.shooter.shooterTopCMD;
import frc.robot.subsystems.AimSub;
import frc.robot.subsystems.ShooterBottomSub;
import frc.robot.subsystems.ShooterFeedSub;
import frc.robot.subsystems.ShooterTopSub;

public class DropFloor extends SequentialCommandGroup {
  /** Creates a new DropFloor. */
  public DropFloor(AimSub aimSub, ShooterBottomSub shooterBottomSub, ShooterTopSub shooterTopSub,
      ShooterFeedSub shooterFeedSub) {
    super(
      (new ShootSpeaker(shooterTopSub, shooterBottomSub)
        .alongWith(
          new aimFloorCMD(aimSub).withTimeout(0.5)
          .andThen(
            new shooterFeedCMD(shooterFeedSub, 1).withTimeout(0.25)
          )
          .andThen(
            new aimZeroCMD(aimSub).withTimeout(1.3)
          )
        )
      )
      .andThen(new shooterTopCMD(shooterTopSub, 0).alongWith(new shooterBottomCMD(shooterBottomSub, 0))).withTimeout(1.3)
    );
  }
}
