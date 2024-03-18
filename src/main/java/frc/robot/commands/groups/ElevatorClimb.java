
package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.elevator.elevatorClimbCMD;
import frc.robot.subsystems.ElevatorSub;

public class ElevatorClimb extends SequentialCommandGroup {
  /** Creates a new landalga. */
  public ElevatorClimb(ElevatorSub _sub) {
    super(
      new elevatorClimbCMD(_sub).withTimeout(2)
    );
  }
}
