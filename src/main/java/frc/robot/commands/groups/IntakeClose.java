
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.intake.redlinedUpCMD;
import frc.robot.subsystems.RotationSub;

public class IntakeClose extends SequentialCommandGroup {
  /** Creates a new landalga. */
  public IntakeClose(RotationSub _sub) {
    super(
      new redlinedUpCMD(_sub).withTimeout(0.65)
    );
  }
}
