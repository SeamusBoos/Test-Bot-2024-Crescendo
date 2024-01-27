package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.maps.TestBotMap;

public class Shooter extends SubsystemBase {
    // motors
    public final CANSparkMax m_LeftShooterMotor = new CANSparkMax(TestBotMap.ShooterConstants.LeftShooterMotorPort, MotorType.kBrushless);
    public final CANSparkMax m_RightShooterMotor = new CANSparkMax(TestBotMap.ShooterConstants.RightShooterMotorPort, MotorType.kBrushless);

    // encoders
    public final RelativeEncoder m_RightShooterMotorEncoder = m_RightShooterMotor.getEncoder();
    public final RelativeEncoder m_LeftShooterMotorEncoder = m_LeftShooterMotor.getEncoder();

    // PID controllers
    private SparkPIDController m_leftShooterPID = m_LeftShooterMotor.getPIDController();
    private SparkPIDController m_rightShooterPID = m_RightShooterMotor.getPIDController();

    public double kP, kI, kD, kIz, kFF, kMaxOut, kMinOut, maxRPM;
    private int shootSpeed;

    public Shooter() {
      m_LeftShooterMotor.setInverted(true);
      m_RightShooterMotor.setInverted(true);

      m_LeftShooterMotorEncoder.setVelocityConversionFactor(1.0);
      m_RightShooterMotorEncoder.setVelocityConversionFactor(1.0);

      m_LeftShooterMotorEncoder.setPosition(0);
      m_RightShooterMotorEncoder.setPosition(0);

      shootSpeed = 2200;

      // set PID constants
      kP = 6e-5;
      kI = 5e-7;
      kD = 1e-10;
      kIz = 0;
      kFF = 1.0/5700.0;
      kMaxOut = 1;
      kMinOut = -1;
      maxRPM = 5700;

      // set PID coefficients
      m_leftShooterPID.setP(kP);
      m_leftShooterPID.setI(kI);
      m_leftShooterPID.setD(kD);
      m_leftShooterPID.setIZone(kIz);
      m_leftShooterPID.setFF(kFF);
      m_leftShooterPID.setOutputRange(kMinOut, kMaxOut);

      m_rightShooterPID.setP(kP);
      m_rightShooterPID.setI(kI);
      m_rightShooterPID.setD(kD);
      m_rightShooterPID.setIZone(kIz);
      m_rightShooterPID.setFF(kFF);
      m_rightShooterPID.setOutputRange(kMinOut, kMaxOut);

    }

    public void runShooter() {
      double leftSetpoint = shootSpeed;
      double rightSetpoint = shootSpeed;

      m_leftShooterPID.setReference(leftSetpoint, CANSparkMax.ControlType.kVelocity);
      m_rightShooterPID.setReference(rightSetpoint, CANSparkMax.ControlType.kVelocity);
    }
    
    public int getShootSpeed() {
      return shootSpeed;
    }

    public void setShootSpeed(int speed) {
      shootSpeed = speed;
    }

    public void dashboardOut() {
        SmartDashboard.putNumber("Left Shoot Enc", m_LeftShooterMotorEncoder.getPosition());
        SmartDashboard.putNumber("Right Shoot Enc", m_RightShooterMotorEncoder.getPosition());

        SmartDashboard.putNumber("Left Shoot Vel", m_LeftShooterMotorEncoder.getVelocity());
        SmartDashboard.putNumber("Right Shoot Vel", m_RightShooterMotorEncoder.getVelocity());
    }

    /*public void move(double speed) {
        m_RightShooterMotor.set(-speed);
        m_LeftShooterMotor.set(-(speed));
      }*/
    
    public double getRightShooterRPM(){
      return m_RightShooterMotorEncoder.getVelocity();
    }

    public double getLeftShooterRPM(){
            return m_LeftShooterMotorEncoder.getVelocity();

    }

    @Override
    public void periodic() {

    }

    public void stop(){
      m_RightShooterMotor.set(0);
      m_LeftShooterMotor.set(0);
    }

    @Override
    public void simulationPeriodic() {

    }
}
