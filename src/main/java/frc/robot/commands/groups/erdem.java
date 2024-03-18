
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.intake.intakeSparkMaxCMD;
import frc.robot.commands.shooter.shooterFeedCMD;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.ShooterFeedSub;

public class erdem extends SequentialCommandGroup {
  /** Creates a new erdem. */
  public erdem(IntakeSub intakeSub, ShooterFeedSub shooterFeedSub) {
    super(
      new intakeSparkMaxCMD(intakeSub, -0.75).withTimeout(0.3)
        .alongWith(new shooterFeedCMD(shooterFeedSub, 0.5)).withTimeout(0.3),

      new shooterFeedCMD(shooterFeedSub, -0.075).withTimeout(0.175)
    );
  }
}
