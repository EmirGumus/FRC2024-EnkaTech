
package frc.robot.subsystems;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.lib.LimelightHelpers;
import frc.robot.Constants;

public class LimeLight3 extends SubsystemBase {
  public final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  // public final NetworkTableEntry ta = table.getEntry("ta");
  public final NetworkTableEntry tx = table.getEntry("tx");
  public final NetworkTableEntry ty = table.getEntry("ty");
  public final NetworkTableEntry tpipeline = table.getEntry("pipeline");
  public static Integer tpipeID;
  public double tid;
  public double distance;

  public LimeLight3() {}

  @Override
  public void periodic() {
    /*
     * tpipeID = RobotContainer.m_limelightchooser.getSelected();
     * if(tpipeID!=null){
     * tpipeline.setNumber(tpipeID);
     * }
    */
    
    if(!DriverStation.getAlliance().isEmpty()){
      if (DriverStation.getAlliance().get() == DriverStation.Alliance.Blue) {
        tpipeID = 0;
        tpipeline.setNumber(tpipeID);
      } else if (DriverStation.getAlliance().get() == DriverStation.Alliance.Red) {
        tpipeID = 1;
        tpipeline.setNumber(tpipeID);
      }
    }

    tid = LimelightHelpers.getFiducialID("limelight");

    // double area = ta.getDouble(0.0);
    //double x = tx.getDouble(0.0);
    //double y = ty.getDouble(0.0);
    //double pipeline = tpipeline.getDouble(0);

    double angleToGoalDegrees = Constants.Vision.MountAngleDegrees + ty.getDouble(0.0);
    double angleToGoalRadians = angleToGoalDegrees * (Math.PI / 180);
    distance = Units.inchesToMeters(
      (Constants.Vision.TagHeightInches - Constants.Vision.LensHeightInches) / Math.tan(angleToGoalRadians)
    ) * 100;

    //SmartDashboard.putNumber("LimelightX", x);
    //SmartDashboard.putNumber("LimelightY", y);
    //SmartDashboard.putNumber("AprilTagID", tid);
    //SmartDashboard.putNumber("pipelineIndex", pipeline);
    SmartDashboard.putNumber("distance", distance);
  }
}
