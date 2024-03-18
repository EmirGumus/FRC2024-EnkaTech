
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.elevator.elevatorPIDDownCMD;
import frc.robot.subsystems.ElevatorSub;

public class ElevatorDown extends SequentialCommandGroup {
  /** Creates a new landalga. */
  public ElevatorDown(ElevatorSub _sub) {
    super(
      new elevatorPIDDownCMD(_sub).withTimeout(5) // 0.075
    );
  }
}
