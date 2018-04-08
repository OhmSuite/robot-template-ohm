package com.team1389.systems;

import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.hardware.inputs.software.RangeIn;
import com.team1389.hardware.outputs.software.PercentOut;
import com.team1389.system.Subsystem;
import com.team1389.util.list.AddList;
import com.team1389.watch.Watchable;

public class TeleopGearIntakeSystem extends Subsystem
{
	RangeIn ctrl;
	PercentOut arm;
	PercentOut intakeVolt;
	DigitalIn intake;
	DigitalIn outtake;
	DigitalIn power;
	public TeleopGearIntakeSystem(RangeIn ctrl, PercentOut arm, PercentOut intakeVolt, DigitalIn intake,
			DigitalIn outtake, DigitalIn power)
	{
		super();
		this.power = power;
		this.ctrl = ctrl;
		this.arm = arm;
		this.intakeVolt = intakeVolt;
		this.intake = intake;
		this.outtake = outtake;
	}
	
	@Override
	public AddList<Watchable> getSubWatchables(AddList<Watchable> arg0)
	{
		return null;
	}
	@Override
	public String getName()
	{
		return null;
	}
	@Override
	public void init()
	{
		
	}
	@Override
	public void update()
	{
		if(power.get())
		{
		arm.set(ctrl.get());
		}
		else 
		{
			arm.set(ctrl.get()/2);
			System.out.println("halved");
		}
		if(intake.get())
		{
			intakeVolt.set(1);
		}
		else if(outtake.get())
		{
			intakeVolt.set(-1);
		}
		else 
		{
			intakeVolt.set(0);
		}
		
	}
	
}
