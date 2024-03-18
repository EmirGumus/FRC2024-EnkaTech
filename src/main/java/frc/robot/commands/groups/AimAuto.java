// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.aim.aimLL3CMD;
import frc.robot.subsystems.AimSub;
import frc.robot.subsystems.LimeLight3;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class AimAuto extends SequentialCommandGroup {
  /** Creates a new AimAuto. */
  public AimAuto(AimSub aimSub, LimeLight3 limeLight3) {
    addCommands(
      new aimLL3CMD(aimSub, limeLight3).withTimeout(1)
    );
  }
}
