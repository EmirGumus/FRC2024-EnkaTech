
package frc.robot;

import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.autos.AimShootAuto;
import frc.robot.autos.IntakeAutoClose;
import frc.robot.autos.IntakeAutoFast;
import frc.robot.autos.IntakeAutoSlow;
import frc.robot.autos.LimeLightAutoTarget;
import frc.robot.autos.ShootAuto;
import frc.robot.commands.groups.AimZero;
import frc.robot.commands.groups.DropFloor;
import frc.robot.commands.groups.ElevatorClimb;
import frc.robot.commands.groups.ElevatorUp;
import frc.robot.commands.groups.IntakeClose;
import frc.robot.commands.groups.IntakeOpen;
import frc.robot.commands.groups.ShootAmp;
import frc.robot.commands.groups.StagePoint;
import frc.robot.commands.groups.erdem;
import frc.robot.commands.groups.kerem;
import frc.robot.commands.intake.intakeSparkMaxCMD;
import frc.robot.commands.shooter.shooterFeedCMD;
import frc.robot.subsystems.AimSub;
import frc.robot.subsystems.ElevatorSub;
import frc.robot.subsystems.IntakeSub;
import frc.robot.subsystems.LimeLight3;
import frc.robot.subsystems.RotationSub;
import frc.robot.subsystems.ShooterBottomSub;
import frc.robot.subsystems.ShooterFeedSub;
import frc.robot.subsystems.ShooterTopSub;
import frc.robot.subsystems.YagslSwerveSub;

public class RobotContainer {
        /* Controllers */
        private final CommandXboxController driver = new CommandXboxController(0);
        private final CommandXboxController operator = new CommandXboxController(1);

        /* Drive Controls */
        private final int translationAxis = XboxController.Axis.kLeftY.value;
        private final int strafeAxis = XboxController.Axis.kLeftX.value;
        private final int rotationAxis = XboxController.Axis.kRightX.value;

        /* Dashboard */
        // public static SendableChooser<Integer> m_limelightchooser = new
        // SendableChooser<>();
        public static SendableChooser<String> m_autochooser = new SendableChooser<>();

        /* Subsystems */
        private final YagslSwerveSub drivebase = new YagslSwerveSub();
        private final LimeLight3 apriltag = new LimeLight3();
        private final RotationSub rotationSub = new RotationSub();
        private final AimSub aimSub = new AimSub();
        private final IntakeSub intakeSub = new IntakeSub();
        private final ShooterBottomSub shooterSub = new ShooterBottomSub();
        private final ShooterTopSub shooterSub1 = new ShooterTopSub();
        private final ShooterFeedSub shooterSub2 = new ShooterFeedSub();
        final ElevatorSub elevatorSub = new ElevatorSub();

        public RobotContainer() {
                Command driveFieldOrientedAnglularVelocity = 
                        drivebase.driveCommand(
                                () -> Math.abs(driver.getRawAxis(translationAxis)) > 0.06
                                        ? driver.getRawAxis(translationAxis) * -1
                                        : 0,
                                () -> Math.abs(driver.getRawAxis(strafeAxis)) > 0.06
                                        ? driver.getRawAxis(strafeAxis) * -1
                                        : 0,
                                () -> Math.abs(driver.getRawAxis(rotationAxis)) > 0.06
                                        ? driver.getRawAxis(rotationAxis) * -0.9
                                        : 0,
                                apriltag,
                                () -> driver.b().getAsBoolean()
                );

                drivebase.setDefaultCommand(driveFieldOrientedAnglularVelocity);

                /*
                 * m_limelightchooser.setDefaultOption(
                 * "Red",
                 * 1
                 * );
                 * 
                 * m_limelightchooser.addOption(
                 * "Blue",
                 * 0
                 * );
                */

                m_autochooser.setDefaultOption(
                        "Hoperlor",
                        "5 Note Mid"
                );

                m_autochooser.addOption(
                        "Orta Saha",
                        "4 Note Back" 
                );

                m_autochooser.addOption(
                        "Sahne Arkasi",
                        "3 Note Stage"
                );

                m_autochooser.addOption(
                        "Test",
                        "New Auto"
                );

                // SmartDashboard.putData("LimeLight Pipeline", m_limelightchooser);
                SmartDashboard.putData("Auto Chooser", m_autochooser);

                NamedCommands.registerCommand("kerem", new AimShootAuto(apriltag, shooterSub, shooterSub1, aimSub, shooterSub2));
                NamedCommands.registerCommand("Intake Open", new IntakeOpen(rotationSub));
                NamedCommands.registerCommand("Intake Close", new IntakeClose(rotationSub));
                NamedCommands.registerCommand("Intake AC", new IntakeAutoClose(intakeSub));
                NamedCommands.registerCommand("Shoot", new ShootAuto(shooterSub1, shooterSub, shooterSub2));
                NamedCommands.registerCommand("IntakeSlow", new IntakeAutoSlow(intakeSub));
                NamedCommands.registerCommand("IntakeFast", new IntakeAutoFast(intakeSub));
                NamedCommands.registerCommand("erdem", new erdem(intakeSub, shooterSub2));
                NamedCommands.registerCommand("limelightT", new LimeLightAutoTarget(drivebase, apriltag));
                NamedCommands.registerCommand("Aim Zero", new AimZero(aimSub));

                configureButtonBindings();
        }

        private void configureButtonBindings() {
                // Driver Buttons
                driver.y().onTrue(new AimZero(aimSub));
                driver.x().onTrue(new ShootAmp(aimSub, shooterSub1, shooterSub, shooterSub2));
                driver.a().onTrue(new DropFloor(aimSub, shooterSub, shooterSub1, shooterSub2));
                driver.leftBumper().whileTrue(new shooterFeedCMD(shooterSub2, 0.1));
                driver.rightBumper().onTrue(new kerem(apriltag, shooterSub, shooterSub1, aimSub, shooterSub2));

                // Operator Buttons
                operator.a().onTrue(new IntakeOpen(rotationSub));
                operator.b().onTrue(new IntakeClose(rotationSub));
                operator.x().whileTrue(new intakeSparkMaxCMD(intakeSub, 0.5));
                operator.y().onTrue(new erdem(intakeSub, shooterSub2));
                operator.leftBumper().onTrue(new ElevatorUp(elevatorSub));
                operator.rightBumper().onTrue(new ElevatorClimb(elevatorSub));
                operator.button(7).onTrue(new StagePoint(aimSub, shooterSub2, shooterSub, shooterSub1));
        }

        public Command getAutonomousCommand() {
                return new PathPlannerAuto(m_autochooser.getSelected());
        }
}
