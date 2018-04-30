
package com.team1389.robot;

import com.team1389.auto.AutoModeBase;
import com.team1389.auto.AutoModeExecuter;
import com.team1389.hardware.inputs.hardware.SpartanGyro;
import com.team1389.operation.TeleopMain;
import com.team1389.system.drive.FourWheelSignal;
import com.team1389.watch.info.NumberInfo;
import com.team1389.watchers.DashboardInput;

import edu.wpi.first.wpilibj.CameraServer;
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
	RobotSoftware robot;
	TeleopMain teleOperator;
	AutoModeExecuter autoModeExecuter;
	SpartanGyro.CalibrateCommand gyroCalib;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit()
	{
		robot = RobotSoftware.getInstance();

		DashboardInput.getInstance().init();
		autoModeExecuter = new AutoModeExecuter();

		teleOperator = new TeleopMain(robot);
		gyroCalib = robot.gyro.new CalibrateCommand(true);

	}

	@Override
	public void autonomousInit()
	{
		autoModeExecuter.stop();
		AutoModeBase selectedAutonMode = DashboardInput.getInstance().getSelectedAutonMode();
		autoModeExecuter.setAutoMode(selectedAutonMode);
		gyroCalib.cancel();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic()
	{
	}

	@Override
	public void disabledInit()
	{

	}

	@Override
	public void disabledPeriodic()
	{
		gyroCalib.exec();
	}

	@Override
	public void teleopInit()
	{
		gyroCalib.cancel();
		autoModeExecuter.stop();
		teleOperator.init();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic()
	{
		teleOperator.periodic();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testInit()
	{

	}
}
