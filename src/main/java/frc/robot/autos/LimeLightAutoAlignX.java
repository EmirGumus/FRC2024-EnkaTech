
package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.lib.oldswerve.SwerveSub;
import frc.robot.subsystems.LimeLight3;

public class LimeLightAutoAlignX extends SequentialCommandGroup {
  public LimeLightAutoAlignX(SwerveSub _swerve, LimeLight3 _limelight) {
    addCommands(
      new RunCommand(()-> 
        _swerve.driveAuto((_limelight.tx.getDouble(0)*-0.05),0,0,true),
        _swerve
      ).withTimeout(1)
    );
  } 
}
