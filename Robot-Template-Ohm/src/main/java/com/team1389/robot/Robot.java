
package com.team1389.robot;

import com.team1389.hardware.registry.Registry;

import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot
{
	Registry registry; 
	@Override
	public void robotInit()
	{
		registry = new Registry();
	}
	
	@Override
	public void teleopInit()
	{
	}
	@Override
	public void teleopPeriodic()
	{
	}

}
