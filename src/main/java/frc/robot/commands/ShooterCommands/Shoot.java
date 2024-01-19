package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shoot extends Command {
    private final Shooter m_shooter; 
    private XboxController driverController = new XboxController(0);

    private double RTrigger = 0.0;
    private double RightShooterRPM = 0.0;
    private double LeftShooterRPM = 0.0;
    private int shooterSpeed = 0;


    // RTrigger = driverController.getRightTriggerAxis();
    // private double RightTrigger driverController.getRightTriggerAxis();
    

    public Shoot(Shooter subsystem){
        m_shooter = subsystem;
        RTrigger = driverController.getRightTriggerAxis();

        addRequirements(subsystem); 
    }

    public void initialize() {}



    @Override
    public void execute() {
        RightShooterRPM = m_shooter.getRightShooterRPM();
        LeftShooterRPM = m_shooter.getLeftShooterRPM();

        SmartDashboard.putNumber("Right Trigger", RTrigger);
        SmartDashboard.putNumber("Shooter Speed", shooterSpeed);
        SmartDashboard.putNumber("Front Shooter RPM", RightShooterRPM);
        SmartDashboard.putNumber("Back Shooter RPM", LeftShooterRPM);

        // press X: plus 500 rpm
        // press B: minus 500 rpm
        // press A: plus 100 rpm
        // press y: minus 100 rpm
        if (driverController.getXButtonPressed()){
            shooterSpeed = shooterSpeed+500;
        }
        else if (driverController.getBButtonPressed()){
            shooterSpeed = shooterSpeed-500;
        }
        else if (driverController.getAButtonPressed()){
            shooterSpeed = shooterSpeed+100;
        }
        else if (driverController.getYButtonPressed()){
            shooterSpeed = shooterSpeed-100;
        }

        // make sure speed doesn't surpass accepted values
        if (shooterSpeed > 5700) {
            shooterSpeed = 5700;
        }
        else if (shooterSpeed < -5700) {
            shooterSpeed = -5700;
        }

        if (driverController.getRightBumper()) {
            m_shooter.setShootSpeed(shooterSpeed);
            m_shooter.runShooter();
        }
        else if (!driverController.getRightBumper()) {
            m_shooter.setShootSpeed(0);
            m_shooter.runShooter();
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.setShootSpeed(0);
        m_shooter.runShooter();
    }
}
