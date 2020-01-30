/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive. DifferentialDrive;
import com.ctre.phoenix.motorcontrol.*;

import frc.robot.utility.*;
import frc.robot.RobotMap;




/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public static final DriveTrain instance = new DriveTrain();

  public static DefaultTalonFXDrive mDriveLeftMaster, mDriveLeftB;
  public static DefaultTalonFXDrive mDriveRightMaster, mDriveRightB;


  public static DriveTrain getInstance(){
    return instance;
  }

  public double TurnRateCurved;
public static DifferentialDrive mDrive;

public DriveTrain(){

  mDriveLeftMaster = new DefaultTalonFXDrive(RobotMap.mDriveLeftA_ID);
  mDriveLeftB = new DefaultTalonFXDrive(RobotMap.mDriveLeftA_ID);

  mDriveRightMaster = new DefaultTalonFXDrive(RobotMap.mDriveRightA_ID);
  mDriveRightB = new DefaultTalonFXDrive(RobotMap.mDriveLeftB_ID);

  mDriveLeftB.set(ControlMode.Follower,RobotMap.mDriveLeftA_ID);
  
  mDriveRightB.set(ControlMode.Follower,RobotMap.mDriveRightA_ID);

}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
