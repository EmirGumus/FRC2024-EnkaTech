
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Channels;

public class AimSub extends SubsystemBase {
  private final CANSparkMax neo = new CANSparkMax(Channels.AimNeo, MotorType.kBrushless);
  public final RelativeEncoder encoder = neo.getEncoder();

  public AimSub() {
    neo.setInverted(true);
    encoder.setPosition(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("AimEncoder", encoder.getPosition() * 100);
  }

  public void runNeo(double speed) {
    neo.set(speed);
  }

  public void stopNeo() {
    neo.set(0);
  }

  public void breakeFW() {
    neo.set(-0.0085);
  }

  public void breakeBW() {
    neo.set(0.025);
  }

  public void resetEncoder() {
    encoder.setPosition(0);
  }
}
