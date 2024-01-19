package frc.robot.maps;

public class TestBotMap {
    public static final class DriveMap {
        public static final int FrontRightMotorPort = 1;
        public static final int FrontLeftMotorPort = 3;
        public static final int BackRightMotorPort = 2;
        public static final int BackLeftMotorPort = 4;

        public static final boolean leftInvert = false;
        public static final boolean rightInvert = true;
    }

    public static final class ShooterConstants {
        public static final int LeftShooterMotorPort = 5;
        public static final int RightShooterMotorPort = 6;

        public static final double LeftShooterSpeed = 0.5;
        public static final double RightShooterSpeed = 0.25;

        public static final double Kp = 1;
        public static final double Ki = 0;
        public static final double Kd = 0;
    }

    public static final class OIMap {
        public static final int kDriverControllerPort = 0;
        public static final int kGunnerControllerPort = 1;
    }
}
