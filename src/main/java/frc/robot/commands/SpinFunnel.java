package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FunnelSpinner;

public class SpinFunnel extends Command {
    private double funnelSpeed = 0.5;
    private static FunnelSpinner funnel;
    public SpinFunnel(FunnelSpinner funnelSS){

        funnel = funnelSS;

        addRequirements(funnel);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        funnel.setSpeed(funnelSpeed);
        funnel.runSpeed();
    }

    @Override
    public void end(boolean interrupted){
        funnel.setSpeed(0);
        funnel.runSpeed();
    }
}
