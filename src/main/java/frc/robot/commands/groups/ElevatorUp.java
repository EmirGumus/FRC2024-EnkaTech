
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.elevator.elevatorPIDUpCMD;
import frc.robot.subsystems.ElevatorSub;

public class ElevatorUp extends SequentialCommandGroup {
  /** Creates a new landalga. */
  public ElevatorUp(ElevatorSub _sub) {
    super(
      new elevatorPIDUpCMD(_sub).withTimeout(2) // 0.075
    );
  }
}
