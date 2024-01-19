package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootPID extends Command {
    private final Shooter m_shooter; 
    private XboxController driverController = new XboxController(0);

    private double RTrigger = 0.0;
    private double RightShooterRPM = 0.0;
    private double LeftShooterRPM = 0.0;
    private double shooterSpeed = 0.0;

    // RTrigger = driverController.getRightTriggerAxis();
    // private double RightTrigger driverController.getRightTriggerAxis();
    

    public ShootPID(Shooter subsystem){
        m_shooter = subsystem;
        RTrigger = driverController.getRightTriggerAxis();

        addRequirements(subsystem);
    }

    public void initialize() {}



    @Override
    public void execute(){

    RightShooterRPM = m_shooter.getRightShooterRPM();
    LeftShooterRPM = m_shooter.getLeftShooterRPM();


    SmartDashboard.putNumber("Right Trigger", RTrigger);
    SmartDashboard.putNumber("Shooter Power", shooterSpeed);
    SmartDashboard.putNumber("Front Shooter RPM", RightShooterRPM);
    SmartDashboard.putNumber("Back Shooter RPM", LeftShooterRPM);



    if (driverController.getXButtonPressed()){
        shooterSpeed = shooterSpeed+0.1;
    }
    else if (driverController.getBButtonPressed()){
        shooterSpeed = shooterSpeed-0.1;
    }
    else if (driverController.getAButtonPressed()){
        shooterSpeed = shooterSpeed+0.05;
    }
    else if (driverController.getYButtonPressed()){
        shooterSpeed = shooterSpeed-0.05;
    }

    if (shooterSpeed>1) {
        shooterSpeed = 1;
    }
    else if (shooterSpeed<-1) {
        shooterSpeed = -1;
    }

    if (driverController.getRightTriggerAxis() > 0.1){
    //   m_shooter.move(driverController.getRightTriggerAxis());
//          m_shooter.move(shooterSpeed);

    }
    
    else {
//      m_shooter.move(0);
    }

}
}
