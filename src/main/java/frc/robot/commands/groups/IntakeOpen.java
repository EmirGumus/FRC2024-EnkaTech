
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.intake.redlineDownCMD;
import frc.robot.subsystems.RotationSub;

public class IntakeOpen extends SequentialCommandGroup {
  /** Creates a new landalga. */
  public IntakeOpen(RotationSub _sub) {
    super(
      new redlineDownCMD(_sub).withTimeout(0.5) //0.075
    );
  }
}
