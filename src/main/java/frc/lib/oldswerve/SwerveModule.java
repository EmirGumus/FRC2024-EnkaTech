
package frc.lib.oldswerve;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.lib.math.Conversions;
import frc.lib.util.CTREModuleState;
import frc.lib.util.SwerveModuleConstants;
import frc.robot.Constants;

public class SwerveModule {
    public int moduleNumber;
    private Rotation2d angleOffset;
    private Rotation2d lastAngle;

    private TalonFX m_angleMotor;
    private TalonFX m_driveMotor;
    private CANcoder angleEncoder;

    private final SimpleMotorFeedforward driveFeedForward = new SimpleMotorFeedforward(Constants.Swerve.DRIVE_S, Constants.Swerve.DRIVE_V, Constants.Swerve.DRIVE_A);

    /* drive motor control requests */
    private final DutyCycleOut driveDutyCycle = new DutyCycleOut(0).withEnableFOC(true);
    private final VelocityVoltage driveVelocity = new VelocityVoltage(0).withEnableFOC(true);

    /* angle motor control requests */
    private final PositionVoltage anglePosition = new PositionVoltage(0);

    public SwerveModule(int moduleNumber, SwerveModuleConstants moduleConstants){
        this.moduleNumber = moduleNumber;
        this.angleOffset = moduleConstants.angleOffset;
        
        /* Angle Encoder Config */
        angleEncoder = new CANcoder(moduleConstants.cancoderID);
        configAngleEncoder();

        /* Angle Motor Config */
        m_angleMotor = new TalonFX(moduleConstants.angleMotorID);
        configAngleMotor();

        /* Drive Motor Config */
        m_driveMotor = new TalonFX(moduleConstants.driveMotorID);
        configDriveMotor();

        lastAngle = getState().angle;
    }

    public void setDesiredState(SwerveModuleState desiredState, boolean isOpenLoop){
        desiredState = CTREModuleState.optimize(desiredState, getState().angle); 
        setAngle(desiredState);
        setSpeed(desiredState, isOpenLoop);
    }

    private void setSpeed(SwerveModuleState desiredState, boolean isOpenLoop){
        if(isOpenLoop){
            driveDutyCycle.Output = desiredState.speedMetersPerSecond / Constants.Swerve.MAX_SPEED;
            m_driveMotor.setControl(driveDutyCycle);
        }
        else {
            driveVelocity.Velocity = Conversions.MPSToTalon(desiredState.speedMetersPerSecond, Constants.Swerve.WHEEL_CIRCUMFERENCE, Constants.Swerve.DRIVE_GEAR_RATIO);
            driveVelocity.FeedForward = driveFeedForward.calculate(desiredState.speedMetersPerSecond);
            m_driveMotor.setControl(driveVelocity);
        }
    }

    private void setAngle(SwerveModuleState desiredState){
        Rotation2d angle = (Math.abs(desiredState.speedMetersPerSecond) <= (Constants.Swerve.MAX_SPEED * 0.01)) ? lastAngle : desiredState.angle; //Prevent rotating module if speed is less then 1%. Prevents Jittering.
        
        anglePosition.Position = Conversions.degreesToTalon(angle.getDegrees(), Constants.Swerve.ANGLE_GEAR_RATIO);
        m_angleMotor.setControl(anglePosition);
        lastAngle = angle;
    }

    private Rotation2d getAngle(){
        return Rotation2d.fromDegrees(Conversions.talonToDegrees(m_angleMotor.getPosition().getValue(), Constants.Swerve.ANGLE_GEAR_RATIO));
    }

    public Rotation2d getCANcoder(){
        return Rotation2d.fromRotations(angleEncoder.getAbsolutePosition().getValue());
    }

    public void resetToAbsolute(){
        double absolutePosition = Conversions.degreesToTalon(getCANcoder().getDegrees() - angleOffset.getDegrees(), Constants.Swerve.ANGLE_GEAR_RATIO);
        m_angleMotor.setPosition(absolutePosition);
    }

    private void configAngleEncoder(){    
        //angleEncoder.getConfigurator().apply(Robot.ctreConfigs.swerveCANcoderConfig);
    }

    private void configAngleMotor(){
        //m_angleMotor.getConfigurator().apply(Robot.ctreConfigs.swerveAngleFXConfig);
        resetToAbsolute();
    }

    private void configDriveMotor(){
        //m_driveMotor.getConfigurator().apply(Robot.ctreConfigs.swerveDriveFXConfig);
        m_driveMotor.getConfigurator().setPosition(0);
    }

    public SwerveModuleState getState(){
        return new SwerveModuleState(
            Conversions.talonToMPS(m_driveMotor.getVelocity().getValue(), Constants.Swerve.WHEEL_CIRCUMFERENCE, Constants.Swerve.DRIVE_GEAR_RATIO), 
            getAngle()
        ); 
    }

    public SwerveModulePosition getPosition(){
        return new SwerveModulePosition(
            Conversions.talonToMeters(m_driveMotor.getPosition().getValue(), Constants.Swerve.WHEEL_CIRCUMFERENCE, Constants.Swerve.DRIVE_GEAR_RATIO), 
            getAngle()
        );
    }
}