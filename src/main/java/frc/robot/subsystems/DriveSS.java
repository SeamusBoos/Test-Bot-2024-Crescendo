package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.maps.TestBotMap;

public class DriveSS extends SubsystemBase{
    public final CANSparkMax m_FrontRightMotor = new CANSparkMax(TestBotMap.DriveMap.FrontRightMotorPort, MotorType.kBrushless);
  public final CANSparkMax m_FrontLeftMotor = new CANSparkMax(TestBotMap.DriveMap.FrontLeftMotorPort, MotorType.kBrushless);
  public final CANSparkMax m_BackRightMotor = new CANSparkMax(TestBotMap.DriveMap.BackRightMotorPort, MotorType.kBrushless);
  public final CANSparkMax m_BackLeftMotor = new CANSparkMax(TestBotMap.DriveMap.BackLeftMotorPort, MotorType.kBrushless);
  /** Creates a new ExampleSubsystem. */
  // private final MotorControllerGroup m_leftmotors;
  // private final MotorControllerGroup m_rightmotors;

  private final DifferentialDrive m_drive;

  public DriveSS() {

    // m_leftmotors = new MotorControllerGroup(
    //   m_BackLeftMotor, m_FrontLeftMotor);

    // m_rightmotors = new MotorControllerGroup(
    //   m_BackRightMotor, m_FrontRightMotor);

    m_FrontLeftMotor.setInverted(TestBotMap.DriveMap.leftInvert);
    m_BackLeftMotor.setInverted(TestBotMap.DriveMap.leftInvert);

    m_FrontRightMotor.setInverted(TestBotMap.DriveMap.leftInvert);
    m_BackRightMotor.setInverted(TestBotMap.DriveMap.leftInvert);

    m_drive = new DifferentialDrive(m_FrontLeftMotor, m_FrontRightMotor);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_drive.tankDrive(leftSpeed, rightSpeed);
  }

}
