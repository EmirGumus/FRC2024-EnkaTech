
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.intake.intakeSparkMaxCMD;
import frc.robot.subsystems.IntakeSub;

public class IntakeShoot extends SequentialCommandGroup {
  /** Creates a new erdem. */
  public IntakeShoot(IntakeSub intakeSub) {
    super(
      new intakeSparkMaxCMD(intakeSub, -0.5).withTimeout(0.3)
    );
  }
}
