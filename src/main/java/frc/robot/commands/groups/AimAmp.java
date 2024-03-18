
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.aim.aimAmpCMD;
import frc.robot.subsystems.AimSub;

public class AimAmp extends SequentialCommandGroup {
  /** Creates a new landalga. */
  public AimAmp(AimSub _sub) {
    super(
      new aimAmpCMD(_sub).withTimeout(1) // 0.075
    );
  }
}
