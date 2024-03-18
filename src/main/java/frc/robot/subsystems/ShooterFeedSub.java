
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Channels;

public class ShooterFeedSub extends SubsystemBase {
  private final CANSparkMax neo3 = new CANSparkMax(Channels.ShooterNeo3, MotorType.kBrushless);

  public ShooterFeedSub() {
    neo3.setInverted(false);
  }

  @Override
  public void periodic() {}

  public void runNeo(double speed){
    neo3.set(speed);
  }

  public void stopNeo(){
    neo3.set(0);
  }
}
