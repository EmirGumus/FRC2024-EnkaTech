
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Channels;

public class ShooterTopSub extends SubsystemBase {
  private final CANSparkMax neo2 = new CANSparkMax(Channels.ShooterNeo2, MotorType.kBrushless);

  public ShooterTopSub() {
    neo2.setInverted(false);
  }

  @Override
  public void periodic() {}

  public void runNeo(double speed) {
    neo2.set(speed);
  }

  public void stageNeo(double speed) {
    neo2.set((0 - speed));
  }

  public void stopNeo() {
    neo2.set(0);
  }
}
