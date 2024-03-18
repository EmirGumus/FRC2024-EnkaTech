
package frc.lib.oldswerve;

import frc.robot.Constants;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import com.kauailabs.navx.frc.AHRS;
import com.pathplanner.lib.auto.AutoBuilder;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveSub extends SubsystemBase {

    //private static final Translation2d[] WHEEL_POSITIONS =
    //    Arrays.copyOf(Constants.moduleTranslations, Constants.moduleTranslations.length);

    public SwerveDriveOdometry swerveOdometry;
    public SwerveModule[] m_swerveMods;
    public AHRS m_gyro;

    public SwerveSub() {

        m_gyro = new AHRS(I2C.Port.kOnboard);
        m_gyro.reset();
        zeroGyro();

        m_swerveMods = new SwerveModule[] {
            new SwerveModule(0, Constants.Swerve.Mod0.constants),
            new SwerveModule(1, Constants.Swerve.Mod1.constants),
            new SwerveModule(2, Constants.Swerve.Mod2.constants),
            new SwerveModule(3, Constants.Swerve.Mod3.constants)
        };

        swerveOdometry = new SwerveDriveOdometry(
            Constants.Swerve.SWERVE_KINEMATICS, 
            getYaw(), 
            getModulePositions());

        AutoBuilder.configureHolonomic(this::getPose, 
                                       this::resetOdometry, 
                                       this::getSpeeds, 
                                       this::driveRobotRelative, 
                                       Constants.AutoConstants.pathFollowerConfig, 
                                       () -> {
                                            var alliance = DriverStation.getAlliance();
                                            if (alliance.isPresent()) {
                                            return alliance.get() == DriverStation.Alliance.Red;
                                            }
                                            return false;
                                        }, 
                                        this);
    }

    public void driveAuto(double xSpeed, double ySpeed, double rot, boolean fieldRelative) {
        var swerveModuleStates =

        Constants.Swerve.SWERVE_KINEMATICS.toSwerveModuleStates(
            fieldRelative
                ? ChassisSpeeds.fromFieldRelativeSpeeds(xSpeed, ySpeed, rot, getYaw())
                : new ChassisSpeeds(xSpeed, ySpeed, rot)
        );

        SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.Swerve.MAX_SPEED);
            for(SwerveModule mod : m_swerveMods){
                mod.setDesiredState(swerveModuleStates[mod.moduleNumber], false);
            }
    }

    public void drive(Translation2d translation, double rotation, boolean fieldRelative, boolean isOpenLoop, boolean isLocked) {
        
        if(isLocked) {

            final SwerveModuleState[] swerveModuleStates = new SwerveModuleState[] {
                new SwerveModuleState(0.1, Rotation2d.fromDegrees(339)),//339
                new SwerveModuleState(0.1, Rotation2d.fromDegrees(315.5)),//315.5
                new SwerveModuleState(0.1, Rotation2d.fromDegrees(269.8)),//269.8
                new SwerveModuleState(0.1, Rotation2d.fromDegrees(182.5))//182.5
            };

            for(SwerveModule mod : m_swerveMods){
                mod.setDesiredState(swerveModuleStates[mod.moduleNumber], isOpenLoop);
            }

        } else {

            //final Translation2d centerOfRotation;

            /*if(fieldRelative) {
                centerOfRotation = getCenterOfRotation(translation.getAngle(), rotation);
            } else {
                centerOfRotation = new Translation2d();
            }*/

            final ChassisSpeeds chassisSpeeds;
        
            if(fieldRelative) {

                chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                    translation.getX(), 
                    translation.getY(), 
                    rotation, 
                    getYaw().times(-1)
                    );

            } else {

                chassisSpeeds = new ChassisSpeeds(
                    translation.getX(), 
                    translation.getY(), 
                    rotation);
            } 

            final var swerveModuleStates = Constants.Swerve.SWERVE_KINEMATICS.toSwerveModuleStates(chassisSpeeds);
            SwerveDriveKinematics.desaturateWheelSpeeds(swerveModuleStates, Constants.Swerve.MAX_SPEED);

            for(SwerveModule mod : m_swerveMods){
                mod.setDesiredState(swerveModuleStates[mod.moduleNumber], isOpenLoop);
            }

        } 

    }    

    /* Used by SwerveControllerCommand in Auto */
    public void setModuleStates(SwerveModuleState[] desiredStates) {
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, Constants.Swerve.MAX_SPEED);
        
        for(SwerveModule mod : m_swerveMods){
            mod.setDesiredState(desiredStates[mod.moduleNumber], false);
        }
    }  
    
    /*private Translation2d getCenterOfRotation(final Rotation2d direction, final double rotation) {
        final var here = new Translation2dPlus(1.0, direction.minus(getYaw()));

        var cwCenter = WHEEL_POSITIONS[0];
        var ccwCenter = WHEEL_POSITIONS[WHEEL_POSITIONS.length - 1];

        for (int i = 0; i < WHEEL_POSITIONS.length - 1; i++) {
            final var cw = WHEEL_POSITIONS[i];
            final var ccw = WHEEL_POSITIONS[i + 1];

            if (here.isWithinAngle(cw, ccw)) {
                cwCenter = ccw;
                ccwCenter = cw;
            }
        }

        // if clockwise
        if (Math.signum(rotation) == 1.0) {
            return cwCenter;
        } else if (Math.signum(rotation) == -1.0) {
            return ccwCenter;
        } else {
            return new Translation2d();
        }
    }*/

    public void driveRobotRelative(ChassisSpeeds robotRelativeSpeeds) {

        ChassisSpeeds targetSpeeds = ChassisSpeeds.discretize(robotRelativeSpeeds, 0.02);
    
        SwerveModuleState[] targetStates = Constants.Swerve.SWERVE_KINEMATICS.toSwerveModuleStates(targetSpeeds);
        setModuleStates(targetStates);

    }

    public ChassisSpeeds getSpeeds() {

        return Constants.Swerve.SWERVE_KINEMATICS.toChassisSpeeds(getModuleStates());

    }

    public Pose2d getPose() {
        return swerveOdometry.getPoseMeters();
    }

    public void resetOdometry(Pose2d pose) {
        swerveOdometry.resetPosition(getYaw(), getModulePositions(), pose);
    }

    public SwerveModuleState[] getModuleStates(){
        SwerveModuleState[] states = new SwerveModuleState[4];
        for(SwerveModule mod : m_swerveMods){
            states[mod.moduleNumber] = mod.getState();
        }
        return states;
    }

    public SwerveModulePosition[] getModulePositions(){
        SwerveModulePosition[] positions = new SwerveModulePosition[4];
        for(SwerveModule mod : m_swerveMods){
            positions[mod.moduleNumber] = mod.getPosition();
        }
        return positions;
    }

    public void zeroGyro(){
        m_gyro.zeroYaw();
    }

    public Rotation2d getYaw() {
        return (Constants.Swerve.INVERT_GYRO) ? Rotation2d.fromDegrees(360 - m_gyro.getYaw()) : Rotation2d.fromDegrees(m_gyro.getYaw());
    }

    public void resetModulesToAbsolute(){
        for(SwerveModule mod : m_swerveMods){
            mod.resetToAbsolute();
        }
    }       

    @Override
    public void periodic(){
        swerveOdometry.update(getYaw(), getModulePositions());  

        SmartDashboard.putNumber("GyroYaw",m_gyro.getYaw());
        //SmartDashboard.putNumber("GyroPitch",m_gyro.getPitch());
        //SmartDashboard.putNumber("GyroRoll",m_gyro.getRoll());

        //for(SwerveModule mod : m_swerveMods){
        //    SmartDashboard.putNumber("Mod " + mod.moduleNumber + " CANcoder", mod.getCANcoder().getDegrees());
        //    SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Integrated", mod.getPosition().angle.getDegrees());
        //    SmartDashboard.putNumber("Mod " + mod.moduleNumber + " Velocity", mod.getState().speedMetersPerSecond);    
        //}
    }
}
