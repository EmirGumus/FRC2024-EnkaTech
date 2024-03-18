
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.aim.aimStageCMD;
import frc.robot.subsystems.AimSub;
import frc.robot.subsystems.ShooterBottomSub;
import frc.robot.subsystems.ShooterFeedSub;
import frc.robot.subsystems.ShooterTopSub;

public class StagePoint extends SequentialCommandGroup {
  /** Creates a new landalga. */
  public StagePoint(AimSub _sub, ShooterFeedSub shooterFeedSub, ShooterBottomSub shooterBottomSub, ShooterTopSub shooterTopSub) {
    super(
      new aimStageCMD(_sub).withTimeout(2), // 0.075
      new ShootStageSlow(shooterFeedSub, shooterBottomSub, shooterTopSub).withTimeout(2)
    );
  }
}
