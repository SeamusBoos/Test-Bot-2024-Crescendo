package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shoot extends Command {
    private final Shooter m_shooter; 
    private XboxController driverController = new XboxController(0);

    private double RTrigger = 0.0;
    private double TopShooterRPM = 0.0;
    private double BottomShooterRPM = 0.0;
    private int topShooterSpeed = 0;
    private int bottomShooterSpeed = 0;
    private boolean readyToShoot = false;


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
        TopShooterRPM = m_shooter.getTopShooterRPM();
        BottomShooterRPM = m_shooter.getBottomShooterRPM();

        SmartDashboard.putNumber("Right Trigger", RTrigger);
        SmartDashboard.putNumber("Top Shooter Setpoint", topShooterSpeed);
        SmartDashboard.putNumber("Bottom Shooter Setpoint", bottomShooterSpeed);

        SmartDashboard.putNumber("Top Shooter RPM", TopShooterRPM);
        SmartDashboard.putNumber("Bottom Shooter RPM", BottomShooterRPM);
        SmartDashboard.putBoolean("Ready To Shoot", readyToShoot);

        if(-m_shooter.getTopShooterRPM()>=topShooterSpeed-20 && -m_shooter.getTopShooterRPM()<=topShooterSpeed+20 && -m_shooter.getBottomShooterRPM()>=bottomShooterSpeed-20 && -m_shooter.getBottomShooterRPM()<=bottomShooterSpeed+20){
            readyToShoot = true;
        }
        else readyToShoot = false;

        // press X: plus 500 rpm
        // press B: minus 500 rpm
        // press A: plus 100 rpm
        // press y: minus 100 rpm
        if (driverController.getYButtonPressed()){
            topShooterSpeed = topShooterSpeed+500;
        }
        else if (driverController.getAButtonPressed()){
            topShooterSpeed = topShooterSpeed-500;
        }
        else if (driverController.getXButtonPressed()){
            topShooterSpeed = topShooterSpeed+100;
        }
        else if (driverController.getBButtonPressed()){
            topShooterSpeed = topShooterSpeed-100;
        }
        else if (driverController.getPOV() == 0){
            bottomShooterSpeed = bottomShooterSpeed+500;
        }
        else if (driverController.getPOV() == 180){
            bottomShooterSpeed = bottomShooterSpeed-500;
        }
        else if (driverController.getPOV() == 270){
            bottomShooterSpeed = bottomShooterSpeed+100;
        }
        else if (driverController.getPOV() == 90){
            bottomShooterSpeed = bottomShooterSpeed-100;
        }

        // make sure speed doesn't surpass accepted values
        if (topShooterSpeed > 5700) {
            topShooterSpeed = 5700;
        }
        else if (topShooterSpeed < -5700) {
            topShooterSpeed = -5700;
        }

        if (bottomShooterSpeed > 5700) {
            bottomShooterSpeed = 5700;
        }
        else if (bottomShooterSpeed < -5700) {
            bottomShooterSpeed = -5700;
        }

        if (driverController.getRightBumper()) {
            m_shooter.setTopShootSpeed(topShooterSpeed);
            m_shooter.setBottomShootSpeed(bottomShooterSpeed);
            m_shooter.runShooter();
        }
        else{
            m_shooter.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.stop();
    }
}
