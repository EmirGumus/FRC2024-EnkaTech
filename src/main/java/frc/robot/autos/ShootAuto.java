
package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.groups.ShootSpeaker;
import frc.robot.commands.shooter.shooterFeedCMD;
import frc.robot.subsystems.ShooterBottomSub;
import frc.robot.subsystems.ShooterFeedSub;
import frc.robot.subsystems.ShooterTopSub;

public class ShootAuto extends SequentialCommandGroup {
  public ShootAuto( 
    ShooterTopSub shooterTopSub, 
    ShooterBottomSub shooterBottomSub,
      ShooterFeedSub shooterFeedSub
    ) {
    super(
      (
        new ShootSpeaker(shooterTopSub, shooterBottomSub).withTimeout(0.35)
      )
      .andThen(
        (
            new ShootSpeaker(shooterTopSub, shooterBottomSub).withTimeout(0.2275)
            .alongWith(new shooterFeedCMD(shooterFeedSub, 1).withTimeout(0.2275))
        )
      )   
    );
  }
}
