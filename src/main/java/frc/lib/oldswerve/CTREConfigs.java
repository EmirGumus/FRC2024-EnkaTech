
package frc.lib.oldswerve;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import frc.robot.Constants;

public final class CTREConfigs {
       public TalonFXConfiguration swerveAngleFXConfig = new TalonFXConfiguration();
       public TalonFXConfiguration swerveDriveFXConfig = new TalonFXConfiguration();
       public CANcoderConfiguration swerveCANcoderConfig = new CANcoderConfiguration();

       public CTREConfigs() {
              /*
               * ============================
               * Swerve Azimuth
               * ==============================
               */

              /* Angle Motor Inverts and Neutral Mode */
              MotorOutputConfigs angleMotorOutput = swerveAngleFXConfig.MotorOutput;
              angleMotorOutput.Inverted = Constants.Swerve.ANGLE_MOTOR_INVERT;
              angleMotorOutput.NeutralMode = Constants.Swerve.AZIMUTH_NEUTRAL_MODE;

              /* Angle Current Limiting */
              CurrentLimitsConfigs angleCurrentLimits = swerveAngleFXConfig.CurrentLimits;
              angleCurrentLimits.SupplyCurrentLimitEnable = Constants.Swerve.AZIMUTH_ENABLE_CURRENT_LIMIT;
              angleCurrentLimits.SupplyCurrentLimit = Constants.Swerve.AZIMUTH_CURRENT_LIMIT;
              angleCurrentLimits.SupplyCurrentThreshold = Constants.Swerve.AZIMUTH_CURRENT_THRESHOLD;
              angleCurrentLimits.SupplyTimeThreshold = Constants.Swerve.AZIMUTH_CURRENT_THRESHOLD_TIME;

              /* Angle PID Config */
              Slot0Configs angleSlot0 = swerveAngleFXConfig.Slot0;
              angleSlot0.kP = Constants.Swerve.AZIMUTH_P;
              angleSlot0.kI = Constants.Swerve.AZIMUTH_I;
              angleSlot0.kD = Constants.Swerve.AZIMUTH_D;

              /*
               * ============================
               * Swerve Drive
               * ==============================
               */

              /* Drive Motor Inverts and Neutral Mode */
              var driveMotorOutput = swerveDriveFXConfig.MotorOutput;
              driveMotorOutput.Inverted = Constants.Swerve.DRIVE_MOTOR_INVERT;
              driveMotorOutput.NeutralMode = Constants.Swerve.DRIVE_NEUTRAL_MODE;

              /* Drive Current Limiting */
              var driveCurrentLimits = swerveDriveFXConfig.CurrentLimits;
              driveCurrentLimits.SupplyCurrentLimitEnable = Constants.Swerve.DRIVE_ENABLE_CURRENT_LIMIT;
              driveCurrentLimits.SupplyCurrentLimit = Constants.Swerve.DRIVE_CURRENT_THRESHOLD_TIME;
              driveCurrentLimits.SupplyCurrentThreshold = Constants.Swerve.DRIVE_CURRENT_THRESHOLD;
              driveCurrentLimits.SupplyTimeThreshold = Constants.Swerve.DRIVE_CURRENT_THRESHOLD_TIME;

              /* Drive PID Config */
              var driveSlot0 = swerveDriveFXConfig.Slot0;
              driveSlot0.kP = Constants.Swerve.DRIVE_P;
              driveSlot0.kI = Constants.Swerve.DRIVE_I;
              driveSlot0.kD = Constants.Swerve.DRIVE_D;

              /* Drive Open and Closed Loop Ramping */
              swerveDriveFXConfig.OpenLoopRamps.DutyCycleOpenLoopRampPeriod = Constants.Swerve.OPEN_LOOP_RAMP;
              swerveDriveFXConfig.OpenLoopRamps.VoltageOpenLoopRampPeriod = Constants.Swerve.OPEN_LOOP_RAMP;

              swerveDriveFXConfig.ClosedLoopRamps.DutyCycleClosedLoopRampPeriod = Constants.Swerve.CLOSED_LOOP_RAMP;
              swerveDriveFXConfig.ClosedLoopRamps.VoltageClosedLoopRampPeriod = Constants.Swerve.CLOSED_LOOP_RAMP;

              /*
               * ============================
               * Swerve CANCoder
               * ==============================
               */

              /** Swerve CANCoder Configuration */
              swerveCANcoderConfig.MagnetSensor.SensorDirection = Constants.Swerve.CANCODER_INVERT;
       }
}
