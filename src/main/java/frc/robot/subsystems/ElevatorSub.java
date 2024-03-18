
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.Channels;

public class ElevatorSub extends SubsystemBase {
  private final CANSparkMax neo1 = new CANSparkMax(Channels.ElevatorNeo1, MotorType.kBrushless);
  private final CANSparkMax neo2 = new CANSparkMax(Channels.ElevatorNeo2, MotorType.kBrushless);
  public final RelativeEncoder encoder = neo1.getEncoder();

  public ElevatorSub() {
    neo1.setInverted(false);
    neo2.setInverted(false);
    encoder.setPosition(0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("ElevatorEncoder", encoder.getPosition());
  }

  public void runNeo(double speed) {
    neo1.set(speed);
    neo2.set(speed);
  }

  public void stopNeo() {
    neo1.set(0);
    neo2.set(0);
  }

  public void breakeNeo() {
    neo1.set(0);// -0.05
    neo2.set(0);// -0.05
  }

  public void climbNeo() {
    neo1.set(0);// 0.05
    neo2.set(0);// 0.05
  }
}
