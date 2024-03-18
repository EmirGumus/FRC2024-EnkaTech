
package frc.robot.subsystems;

import java.io.File;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.util.HolonomicPathFollowerConfig;
import com.pathplanner.lib.util.PIDConstants;
import com.pathplanner.lib.util.ReplanningConfig;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import swervelib.SwerveDrive;
import swervelib.parser.SwerveParser;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;

public class YagslSwerveSub extends SubsystemBase {
    public final SwerveDrive swerveDrive;
    double maximumSpeed = Units.feetToMeters(13);
    File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(), "swerve");

    public YagslSwerveSub() {
        try {
            swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(maximumSpeed);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        SwerveDriveTelemetry.verbosity = TelemetryVerbosity.HIGH;
        setupPathPlanner();
    }

    public void setupPathPlanner() {
        AutoBuilder.configureHolonomic(
            this::getPose, // Robot pose supplier
            this::resetOdometry, // Method to reset odometry (will be called if your auto has a starting pose)
            this::getRobotVelocity, // ChassisSpeeds supplier. MUST BE ROBOT RELATIVE
            this::setChassisSpeeds, // Method that will drive the robot given ROBOT RELATIVE ChassisSpeeds
            new HolonomicPathFollowerConfig( // HolonomicPathFollowerConfig, this should likely live in your
                new PIDConstants(0.7, 0, 0.06),
                // Translation PID constants
                new PIDConstants(0.4, 0, 0.01),
                // Rotation PID constants
                3.8,
                // Max module speed, in m/s
                swerveDrive.swerveDriveConfiguration.getDriveBaseRadiusMeters(),
                // Drive base radius in meters. Distance from robot center to furthest module.
                new ReplanningConfig()
            // Default path replanning config. See the API for the options here
            ),
            () -> {
                // Boolean supplier that controls when the path will be mirrored for the red
                // alliance
                // This will flip the path being followed to the red side of the field.
                // THE ORIGIN WILL REMAIN ON THE BLUE SIDE
                var alliance = DriverStation.getAlliance();
                return alliance.isPresent() 
                    ? alliance.get() == DriverStation.Alliance.Red 
                    : false;
            },
            this // Reference to this subsystem to set requirements
        );
    }

    public void resetOdometry(Pose2d initialHolonomicPose) {
        swerveDrive.resetOdometry(initialHolonomicPose);
    }

    public Pose2d getPose() {
        return swerveDrive.getPose();
    }

    public void setChassisSpeeds(ChassisSpeeds chassisSpeeds) {
        swerveDrive.setChassisSpeeds(chassisSpeeds);
    }

    public ChassisSpeeds getRobotVelocity() {
        return swerveDrive.getRobotVelocity();
    }

    /*
     * public Command aimAtTarget(LimeLight3 ll){
     * return run(()->{
     * double targetx= ll.tx.getDouble(0);
     * if (targetx != 0){
     * swerveDrive.drive(getTargetSpeeds(0,0,Rotation2d.fromDegrees(targetx)));
     * }
     * });
     * }
     * 
     * public ChassisSpeeds getTargetSpeeds(double xInput, double yInput, Rotation2d
     * angle){
     * xInput = Math.pow(xInput, 3);
     * yInput = Math.pow(yInput, 3);
     * return swerveDrive.swerveController.getTargetSpeeds(xInput, yInput,
     * angle.getRadians(),getHeading().getRadians(),maximumSpeed);
     * }
     * public Rotation2d getHeading(){
     * return getPose().getRotation();
     * }
    */

    /**
     * Command to drive the robot using translative values and heading as a
     * setpoint.
     *
     * @param translationX Translation in the X direction.
     * @param translationY Translation in the Y direction.
     * @param headingX     Heading X to calculate angle of the joystick.
     * @param headingY     Heading Y to calculate angle of the joystick.
     * @return Drive command.
    */

    /**
     * Command to drive the robot using translative values and heading as angular
     * velocity.
     *
     * @param translationX     Translation in the X direction.
     * @param translationY     Translation in the Y direction.
     * @param angularRotationX Rotation of the robot to set
     * @return Drive command.
    */

    public Command driveCommand(
        DoubleSupplier translationX, 
        DoubleSupplier translationY,
        DoubleSupplier angularRotationX, 
        LimeLight3 apriltag, 
        BooleanSupplier button
    ) {
        return run(() -> {
            if(apriltag.tid != -1 && button.getAsBoolean()){
                drive(
                    swerveDrive.swerveController.getRawTargetSpeeds(
                        MathUtil.applyDeadband(
                        translationX.getAsDouble() * swerveDrive.getMaximumVelocity(),
                        0.1),
                    MathUtil.applyDeadband(
                        translationY.getAsDouble() * swerveDrive.getMaximumVelocity(),
                        0.1),
                        -apriltag.tx.getDouble(0)*0.12
                    )
                );
            } else{
                swerveDrive.drive(new Translation2d(translationX.getAsDouble() * swerveDrive.getMaximumVelocity(),
                    translationY.getAsDouble() * swerveDrive.getMaximumVelocity()),
                    angularRotationX.getAsDouble() * swerveDrive.getMaximumAngularVelocity(),
                    true,
                    false
                );
            }
        });
    }

    public void drive(ChassisSpeeds velocity) {
        swerveDrive.drive(velocity);
    }

}
