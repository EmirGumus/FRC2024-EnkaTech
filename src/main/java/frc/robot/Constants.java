
package frc.robot;

import java.util.NavigableMap;
import java.util.TreeMap;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import frc.lib.util.COTSFalconSwerveConstants;
import frc.lib.util.SwerveModuleConstants;

public final class Constants {
    public static final class Values {
        public static final double intakeKP = 0.95; // 0.175
        public static final double elevatorKP = 0.05;
        public static final double aimKP = 0.1;
        public static final double intakeDist = -1.05;
        public static final double elevatorDist = -205; // -50 -52
        public static final double aimDistFW = -3; //-5  -5.214279174804688 -3.595234870910645
        public static final double aimDistBW = -1.15; // -0.785714507102966 -0.761904954910278
        public static final double aimDistIncrease = 1;
    }

    public static final class Channels {
        public static final int IntakeRotate = 5;
        public static final int IntakeNeo = 4;
        public static final int ElevatorNeo1 = 2;
        public static final int ElevatorNeo2 = 3;
        public static final int AimNeo = 8;
        public static final int ShooterNeo1 = 6; // bottom
        public static final int ShooterNeo2 = 7; // top
        public static final int ShooterNeo3 = 9; // feed
        public static final int DutyCycleEncoder = 0;
    }

    public static final class Vision {
        public static final double MountAngleDegrees = 33.3;
        public static final double LensHeightInches = Units.metersToInches(0.435);
        public static final double TagHeightInches = Units.metersToInches(1.47);

        public static NavigableMap<Double, Double> kHoodMap = new TreeMap<Double, Double>();

        static {
            kHoodMap.put(105.0, 0.0); // kesin
            kHoodMap.put(175.0, -330.0); // kesin
            kHoodMap.put(205.5, -480.0); // kesin
            kHoodMap.put(225.0, -545.0); // kesin
            kHoodMap.put(260.0, -630.0); // kesin
            kHoodMap.put(280.0, -700.0); // kesin
            kHoodMap.put(300.0, -727.5); // kesin
            kHoodMap.put(320.25, -740.0); // kesin
            kHoodMap.put(345.0, -780.0); // kesin
            kHoodMap.put(360.0, -807.5); // kesin
            kHoodMap.put(380.0, -820.0); // kesin
            kHoodMap.put(400.0, -850.0); // kesin
            kHoodMap.put(425.0, -855.0); // 
            kHoodMap.put(575.0, -906.0); // rastgele
        }
    }

    public static final class Swerve {
        public static final double stickDeadband = 0.1;
        /* Gyro Constants */
        public static final boolean INVERT_GYRO = false; // Always ensure Gyro is CCW+ CW-

        /* Chosen Module */
        public static final COTSFalconSwerveConstants chosenModule = COTSFalconSwerveConstants
                .SDSMK4i(COTSFalconSwerveConstants.driveGearRatios.SDSMK4i_L1);

        /* Drivetrain Constants */
        public static final double TRACK_WIDTH = 0.576; // Units.inchesToMeters(20.75);
        public static final double WHEEL_BASE = 0.576; // Units.inchesToMeters(20.75);
        public static final double WHEEL_CIRCUMFERENCE = chosenModule.wheelCircumference;

        /* Swerve Kinematics */
        public static final Translation2d[] moduleTranslations = new Translation2d[] {
                new Translation2d(WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
                new Translation2d(WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0),
                new Translation2d(-WHEEL_BASE / 2.0, TRACK_WIDTH / 2.0),
                new Translation2d(-WHEEL_BASE / 2.0, -TRACK_WIDTH / 2.0) };

        public static final SwerveDriveKinematics SWERVE_KINEMATICS = new SwerveDriveKinematics(moduleTranslations);

        public static final double DRIVETRAIN_RADIUS = Math.abs(Math.abs(TRACK_WIDTH * Math.sqrt(2)) / 2);
        // Units.inchesToMeters(14.67247);

        /* Module Gear Ratios */
        public static final double DRIVE_GEAR_RATIO = chosenModule.driveGearRatio;
        public static final double ANGLE_GEAR_RATIO = chosenModule.angleGearRatio;

        /* Motor Inverts */
        public static final InvertedValue ANGLE_MOTOR_INVERT = InvertedValue.Clockwise_Positive;
        public static final InvertedValue DRIVE_MOTOR_INVERT = InvertedValue.Clockwise_Positive;

        /* Angle Encoder Invert */
        public static final SensorDirectionValue CANCODER_INVERT = chosenModule.cancoderInvert;

        /* Swerve Current Limiting */
        public static final int AZIMUTH_CURRENT_LIMIT = 25;
        public static final int AZIMUTH_CURRENT_THRESHOLD = 40;
        public static final double AZIMUTH_CURRENT_THRESHOLD_TIME = 0.1;
        public static final boolean AZIMUTH_ENABLE_CURRENT_LIMIT = true;

        public static final int DRIVE_CURRENT_LIMIT = 35; // 50
        public static final int DRIVE_CURRENT_THRESHOLD = 60;
        public static final double DRIVE_CURRENT_THRESHOLD_TIME = 0.1;
        public static final boolean DRIVE_ENABLE_CURRENT_LIMIT = true;

        public static final double OPEN_LOOP_RAMP = 0.25;
        public static final double CLOSED_LOOP_RAMP = 0;

        /* Angle Motor PID Values */
        public static final double AZIMUTH_P = chosenModule.angleKP;
        public static final double AZIMUTH_I = chosenModule.angleKI;
        public static final double AZIMUTH_D = chosenModule.angleKD;

        /* Drive Motor PID Values */
        public static final double DRIVE_P = 0.05; // 0.12
        public static final double DRIVE_I = 0.0;
        public static final double DRIVE_D = 0.0;
        public static final double DRIVE_F = 0.0;

        /* Drive Motor Characterization Values From SYSID */
        public static final double DRIVE_S = (0.32 / 12); // 0.32
        public static final double DRIVE_V = (1.51 / 12); // 1.51
        public static final double DRIVE_A = (0.27 / 12); // 0.27

        public static final double RATE_LIMITER = 1.5;

        /* Swerve Profiling Values */
        /** Meters per Second */
        public static final double MAX_SPEED = Units.feetToMeters(17.1);
        /** Radians per Second */
        public static final double MAX_ANGULAR_VELOCITY = 10; // Math.PI * 4.12 * 0.5;

        /* Neutral Modes */
        public static final NeutralModeValue AZIMUTH_NEUTRAL_MODE = NeutralModeValue.Coast;
        public static final NeutralModeValue DRIVE_NEUTRAL_MODE = NeutralModeValue.Brake;

        /* Module Specific Constants */
        /* Front Left Module - Module 0 */
        public static final class Mod0 {
            public static final int driveMotorID = 30;
            public static final int angleMotorID = 31;
            public static final int canCoderID = 32;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(170 + 180);// 166
            public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID,
                    canCoderID, angleOffset);
        }

        /* Front Right Module - Module 1 */
        public static final class Mod1 {
            public static final int driveMotorID = 33;
            public static final int angleMotorID = 34;
            public static final int canCoderID = 35;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(139.5 + 180);// 135,5
            public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID,
                    canCoderID, angleOffset);
        }

        /* Back Left Module - Module 2 */
        public static final class Mod2 {
            public static final int driveMotorID = 39;
            public static final int angleMotorID = 40;
            public static final int canCoderID = 41;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(96.1 + 180);// 89.8
            public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID,
                    canCoderID, angleOffset);
        }

        /* Back Right Module - Module 3 */
        public static final class Mod3 {
            public static final int driveMotorID = 36;
            public static final int angleMotorID = 37;
            public static final int canCoderID = 38;
            public static final Rotation2d angleOffset = Rotation2d.fromDegrees(6.5 + 180);// 2.5
            public static final SwerveModuleConstants constants = new SwerveModuleConstants(driveMotorID, angleMotorID,
                    canCoderID, angleOffset);
        }

    }

    public static final class AutoConstants {
        public static final double kMaxSpeedMetersPerSecond = 4.5;
        public static final double kMaxAccelerationMetersPerSecondSquared = 10;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI * Math.PI;

        public static final double kPXController = 1;
        public static final double kPYController = 1;
        public static final double kPThetaController = 1;

        /* Constraint for the motion profilied robot angle controller */
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
                kMaxAngularSpeedRadiansPerSecond,
                kMaxAngularSpeedRadiansPerSecondSquared);

        /* Pathplanner Auton Config */
        public static final HolonomicPathFollowerConfig pathFollowerConfig = new HolonomicPathFollowerConfig(
                new PIDConstants(kPXController, 0, 0), // Translation constants
                new PIDConstants(kPYController, 0, 0), // Rotation constants
                Constants.Swerve.MAX_SPEED,
                Constants.Swerve.DRIVETRAIN_RADIUS,
                new ReplanningConfig());
    }
}
