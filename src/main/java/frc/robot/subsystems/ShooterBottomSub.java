
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Channels;

public class ShooterBottomSub extends SubsystemBase {
  public final CANSparkMax neo1 = new CANSparkMax(Channels.ShooterNeo1, MotorType.kBrushless);

  public ShooterBottomSub() {
    neo1.setInverted(false);
  }

  @Override
  public void periodic() {}

  public void runNeo(double speed) {
    neo1.set(speed);
  }

  public void stopNeo() {
    neo1.set(0);
  }
}
