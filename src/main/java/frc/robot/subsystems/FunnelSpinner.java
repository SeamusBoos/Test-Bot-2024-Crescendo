package frc.robot.subsystems;

import frc.Mechanisms.configurationTypes.OneMotorSSwithSparkMax;
import frc.robot.SubmoduleSubsystemConstants.ConstFunnel;

public class FunnelSpinner extends OneMotorSSwithSparkMax{

    public FunnelSpinner() {
        super(ConstFunnel.funnelMotorCanID, false, 1, "Funnel");
        
    }
    
}
