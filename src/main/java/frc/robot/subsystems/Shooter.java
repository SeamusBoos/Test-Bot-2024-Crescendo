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
    public final CANSparkMax m_TopShooterMotor = new CANSparkMax(TestBotMap.ShooterConstants.TopShooterMotorPort, MotorType.kBrushless);
    public final CANSparkMax m_BottomShooterMotor = new CANSparkMax(TestBotMap.ShooterConstants.BottomShooterMotorPort, MotorType.kBrushless);

    // encoders
    public final RelativeEncoder m_TopShooterMotorEncoder = m_TopShooterMotor.getEncoder();
    public final RelativeEncoder m_BottomShooterMotorEncoder = m_BottomShooterMotor.getEncoder();

    // PID controllers
    private SparkPIDController m_bottomShooterPID = m_BottomShooterMotor.getPIDController();
    private SparkPIDController m_topShooterPID = m_TopShooterMotor.getPIDController();

    public double kP, kI, kD, kIz, kFF, kMaxOut, kMinOut, maxRPM;
      private int topShootSpeed;
    private int bottomShootSpeed;
  

    public Shooter() {
      m_BottomShooterMotor.setInverted(true);
      m_TopShooterMotor.setInverted(true);

      m_BottomShooterMotorEncoder.setVelocityConversionFactor(1.0);
      m_TopShooterMotorEncoder.setVelocityConversionFactor(1.0);

      m_BottomShooterMotorEncoder.setPosition(0);
      m_TopShooterMotorEncoder.setPosition(0);

      bottomShootSpeed = 0;
      topShootSpeed = 0;


      // set PID constants
      kP = 6e-5;
      kI = 5e-9;
      kD = 1e-4;
      kIz = 0;
      kFF = 1.0/5700.0;
      kMaxOut = 1;
      kMinOut = -1;
      maxRPM = 5700;

      // set PID coefficients
      m_bottomShooterPID.setP(kP);
      m_bottomShooterPID.setI(kI);
      m_bottomShooterPID.setD(kD);
      m_bottomShooterPID.setIZone(kIz);
      m_bottomShooterPID.setFF(kFF);
      m_bottomShooterPID.setOutputRange(kMinOut, kMaxOut);

      m_topShooterPID.setP(kP);
      m_topShooterPID.setI(kI);
      m_topShooterPID.setD(kD);
      m_topShooterPID.setIZone(kIz);
      m_topShooterPID.setFF(kFF);
      m_topShooterPID.setOutputRange(kMinOut, kMaxOut);

    }

    public void runShooter() {
      double bottomSetpoint = bottomShootSpeed;
      double topSetpoint = topShootSpeed;

      m_topShooterPID.setReference(-topSetpoint, CANSparkMax.ControlType.kVelocity);
      m_bottomShooterPID.setReference(-bottomSetpoint, CANSparkMax.ControlType.kVelocity);
    }
    
    public int getTopShootSpeed() {
      return topShootSpeed;
    }

    public int getBottomShootSpeed() {
      return bottomShootSpeed;
    }

    public void setTopShootSpeed(int speed) {
      topShootSpeed = speed;
    }

    public void setBottomShootSpeed(int speed) {
      bottomShootSpeed = speed;
    }

    public void dashboardOut() {
        SmartDashboard.putNumber("Left Shoot Enc", m_BottomShooterMotorEncoder.getPosition());
        SmartDashboard.putNumber("Right Shoot Enc", m_TopShooterMotorEncoder.getPosition());

        SmartDashboard.putNumber("Left Shoot Vel", m_BottomShooterMotorEncoder.getVelocity());
        SmartDashboard.putNumber("Right Shoot Vel", m_TopShooterMotorEncoder.getVelocity());
    }

    /*public void move(double speed) {
        m_RightShooterMotor.set(-speed);
        m_LeftShooterMotor.set(-(speed));
      }*/
    
    public double getTopShooterRPM(){
      return m_TopShooterMotorEncoder.getVelocity();
    }

    public double getBottomShooterRPM(){
            return m_BottomShooterMotorEncoder.getVelocity();

    }

    @Override
    public void periodic() {

    }

    public void stop(){
      m_TopShooterMotor.set(0);
      m_BottomShooterMotor.set(0);
    }

    @Override
    public void simulationPeriodic() {

    }
}
