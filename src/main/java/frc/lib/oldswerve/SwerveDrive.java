
package frc.lib.oldswerve;

import frc.robot.Constants;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;


public class SwerveDrive extends Command {    
    private SwerveSub m_swerveDrivetrain;    
    private DoubleSupplier m_translationSup;
    private DoubleSupplier m_strafeSup;
    private DoubleSupplier m_rotationSup;
    private BooleanSupplier m_robotCentricSup;
    private SlewRateLimiter m_xAxisLimiter;
    private SlewRateLimiter m_yAxisLimiter;
    private BooleanSupplier m_isLocked;

    public SwerveDrive(SwerveSub swerveDrivetrain, 
                       DoubleSupplier translationSup, 
                       DoubleSupplier strafeSup, 
                       DoubleSupplier rotationSup, 
                       BooleanSupplier robotCentricSup, 
                       BooleanSupplier isLocked) {

        m_swerveDrivetrain = swerveDrivetrain;
        addRequirements(m_swerveDrivetrain);

        m_translationSup = translationSup;
        m_strafeSup = strafeSup;
        m_rotationSup = rotationSup;
        m_robotCentricSup = robotCentricSup;
        m_isLocked = isLocked;

        m_xAxisLimiter = new SlewRateLimiter(Constants.Swerve.RATE_LIMITER);
        m_yAxisLimiter = new SlewRateLimiter(Constants.Swerve.RATE_LIMITER);

    }

    @Override
    public void execute() {
        /* Get Values, Deadband*/
        double xAxis = MathUtil.applyDeadband(m_translationSup.getAsDouble(), Constants.Swerve.stickDeadband);
        double yAxis = MathUtil.applyDeadband(m_strafeSup.getAsDouble(), Constants.Swerve.stickDeadband);
        double rAxis = MathUtil.applyDeadband(m_rotationSup.getAsDouble(), Constants.Swerve.stickDeadband);

        double xAxisSquared = xAxis * xAxis * Math.signum(xAxis);
        double yAxisSquared = yAxis * yAxis * Math.signum(yAxis);
        double rAxisSquared = rAxis * rAxis * Math.signum(rAxis);

        double xAxisFiltered = m_xAxisLimiter.calculate(xAxisSquared);
        double yAxisFiltered = m_yAxisLimiter.calculate(yAxisSquared);

        /* Drive */
        m_swerveDrivetrain.drive(
            new Translation2d(-xAxisFiltered, -yAxisFiltered).times(Constants.Swerve.MAX_SPEED), 
            -rAxisSquared * Constants.Swerve.MAX_ANGULAR_VELOCITY, 
            !m_robotCentricSup.getAsBoolean(), 
            true,
            m_isLocked.getAsBoolean()
        );
    }
}