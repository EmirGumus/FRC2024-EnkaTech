
package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.LimeLight3;
import frc.robot.subsystems.YagslSwerveSub;

public class LimeLightAutoTarget extends SequentialCommandGroup {
  public LimeLightAutoTarget(YagslSwerveSub _swerve, LimeLight3 _limelight) {
    addCommands(
      new RunCommand(
        () -> _swerve.drive(
          _swerve.swerveDrive.swerveController.getRawTargetSpeeds(
            0,
            0,
            -_limelight.tx.getDouble(0) * 0.085
          )
        ),
        _swerve
      ).withTimeout(0.35));
  }
}
