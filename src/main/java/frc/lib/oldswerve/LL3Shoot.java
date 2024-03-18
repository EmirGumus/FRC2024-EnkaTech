
package frc.lib.oldswerve;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autos.LimeLightAutoTarget;
import frc.robot.commands.aim.aimZeroCMD;
import frc.robot.commands.aim.aimDistanceCMD;
import frc.robot.commands.shooter.shooterBottomCMD;
import frc.robot.commands.shooter.shooterFeedCMD;
import frc.robot.commands.shooter.shooterTopCMD;
import frc.robot.subsystems.AimSub;
import frc.robot.subsystems.LimeLight3;
import frc.robot.subsystems.ShooterBottomSub;
import frc.robot.subsystems.ShooterFeedSub;
import frc.robot.subsystems.ShooterTopSub;
import frc.robot.subsystems.YagslSwerveSub;

public class LL3Shoot extends SequentialCommandGroup {
  /** Creates a new LL3Shoot. */
  public LL3Shoot(YagslSwerveSub swerveDrivetrain, LimeLight3 limeLight3, AimSub aimSub, ShooterTopSub shooterTopSub,
      ShooterBottomSub shooterBottomSub, ShooterFeedSub shooterFeedSub) {
    addCommands(
        new LimeLightAutoTarget(swerveDrivetrain, limeLight3),

        new aimDistanceCMD(aimSub, limeLight3).withTimeout(0.5).alongWith(
            new shooterTopCMD(shooterTopSub, 1).withTimeout(0.5)
                .alongWith(new shooterBottomCMD(shooterBottomSub, 1)).withTimeout(0.5))
            .andThen(
                ((new shooterFeedCMD(shooterFeedSub, 1).withTimeout(0.5)
                    .alongWith(new shooterBottomCMD(shooterBottomSub, 1).withTimeout(0.5)))
                    .alongWith(new shooterTopCMD(shooterTopSub, 1).withTimeout(0.5))).andThen(
                        new aimZeroCMD(aimSub).withTimeout(0.5))));
  }
}
