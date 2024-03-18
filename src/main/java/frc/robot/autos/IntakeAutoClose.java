// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.autos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.intake.intakeSparkMaxCMD;
import frc.robot.subsystems.IntakeSub;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeAutoClose extends SequentialCommandGroup {
  /** Creates a new IntakeAuto. */
  public IntakeAutoClose(IntakeSub intakeSub) {
    super(
      new intakeSparkMaxCMD(intakeSub, 0.75).withTimeout(0.3));
  }
}
