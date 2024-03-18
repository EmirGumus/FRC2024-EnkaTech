
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.aim.aimZeroCMD;
import frc.robot.subsystems.AimSub;

public class AimZero extends SequentialCommandGroup {
  /** Creates a new landalga. */
  public AimZero(AimSub _sub) {
    super(
      new aimZeroCMD(_sub).withTimeout(1) // 0.075
    );
  }
}
