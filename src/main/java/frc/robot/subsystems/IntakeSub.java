// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Channels;

public class IntakeSub extends SubsystemBase {
  private final CANSparkMax neo = new CANSparkMax(Channels.IntakeNeo, MotorType.kBrushless);

  public IntakeSub() {
    neo.setInverted(true);
  }

  @Override
  public void periodic() {}

  public void runNeo(double speed) {
    neo.set(speed);
  }

  public void stopNeo() {
    neo.set(0);
  }
}
