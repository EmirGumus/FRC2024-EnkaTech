
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.shooter.shooterBottomCMD;
import frc.robot.commands.shooter.shooterFeedCMD;
import frc.robot.commands.shooter.shooterTopCMD;
import frc.robot.subsystems.ShooterBottomSub;
import frc.robot.subsystems.ShooterFeedSub;
import frc.robot.subsystems.ShooterTopSub;

public class ShootStageSlow extends SequentialCommandGroup {
  public ShootStageSlow(ShooterFeedSub shooterFeedSub, ShooterBottomSub shooterBottomSub, ShooterTopSub shooterTopSub) {
    super(
      new shooterFeedCMD(shooterFeedSub, -0.25).withTimeout(2)
      .andThen(
        new shooterTopCMD(shooterTopSub, 0.5).withTimeout(0.75)
       .alongWith(new shooterBottomCMD(shooterBottomSub, 0.1)).withTimeout(0.75)
       .alongWith(new shooterFeedCMD(shooterFeedSub, 0.25)).withTimeout(0.75)
      )
      
    );
  }
}
