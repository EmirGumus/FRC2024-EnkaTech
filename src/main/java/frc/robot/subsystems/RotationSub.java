
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Channels;

public class RotationSub extends SubsystemBase {
  private final CANSparkMax redline = new CANSparkMax(Channels.IntakeRotate, MotorType.kBrushed);
  public final RelativeEncoder intakeEncoder1 = redline.getAlternateEncoder(
    SparkMaxAlternateEncoder.Type.kQuadrature,8192
  );

  public RotationSub() {
    redline.setInverted(true);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Intake Encoder", intakeEncoder1.getPosition());
  }

  public void runRedline(double speed) {
    redline.set(speed);
  }

  public void stopRedline() {
    redline.set(0);
  }
}
