package com.team1389.operation;

import java.util.function.Supplier;

import com.team1389.hardware.controls.ControlBoard;
import com.team1389.hardware.inputs.software.DigitalIn;
import com.team1389.robot.RobotSoftware;
import com.team1389.system.SystemManager;
import com.team1389.system.drive.OctocanumSystem;
import com.team1389.system.drive.OctocanumSystem.DriveMode;
import com.team1389.systems.ClimberSystem;
import com.team1389.systems.GearIntakeSystem;
import com.team1389.systems.TeleopGearIntakeSystem;
import com.team1389.systems.TeleopHopperSystem;


/**
 * system manager
 * 
 * @author Quunii
 *
 */
public class TeleopMain
{
	SystemManager manager;
	ControlBoard controls;
	RobotSoftware robot;
	DigitalIn timeRunning;

	/**
	 * 
	 * @param robot
	 *            container of all ohm streams
	 */
	public TeleopMain(RobotSoftware robot)
	{
		this.robot = robot;
	}

	/**
	 * initializes systems, and adds them to a list of systems to update
	 */
	public void init()
	{
		controls = ControlBoard.getInstance();
		OctocanumSystem drive = setupDrive();
	//	GearIntakeSystem gearIntake = setupGearIntake(drive.getDriveModeTracker());
		//Subsystem climbing = setUpClimbing();
		manager = new SystemManager(drive, setupGearIntake(), setupHopper());
		manager.init();
	}

	/**
	 * 
	 * @return a new OctocanumSystem
	 */
	//drive straight is actually quickturn
	private OctocanumSystem setupDrive()
	{
		return new OctocanumSystem(robot.voltageDrive.forEach(op -> op.scale(.5)), robot.pistons, robot.gyroInput, controls.xDriveX(),
				controls.xLeftDriveY(), controls.driveYaw(), controls.driveTrim(), new DigitalIn(()-> false),
				controls.xDriveStraightButton());
	
	}

	/**
	 * 
	 * @return a new GearIntakeSystem
	 */
	private TeleopGearIntakeSystem setupGearIntake()
	{
		
		return new TeleopGearIntakeSystem(controls.leftStickYAxis(), robot.armElevator.getVoltageController().invert(), robot.gearIntake.getVoltageController(), controls.aButton(), controls.bButton(), controls.yButton());
	}

	/**
	 * 
	 * @return a new ClimberSystem
	 */
	private ClimberSystem setUpClimbing()
	{
		return new ClimberSystem(controls.leftTrigger(), robot.climberVoltage);
	}

	private TeleopHopperSystem setupHopper()
	{
		
		return new TeleopHopperSystem(robot.dumperPistonRight.getDigitalOut(), robot.dumperPistonLeft.getDigitalOut(),
				robot.dumperPistonRight.getDigitalOut(), new DigitalIn(() -> true), controls.upDPad(),
				controls.downDPad());
	}
	

	/**
	 * periodically calls update method in all subsystems
	 */
	public void periodic()
	{
		manager.update();
		if (controls.backButton().get())
		{
			robot.zeroAngle();
		}
	}

}
