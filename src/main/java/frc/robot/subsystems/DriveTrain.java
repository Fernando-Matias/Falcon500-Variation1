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
import frc.robot.Constants;




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

  //Setting both falcons to the default fx script
  mDriveLeftMaster = new DefaultTalonFXDrive(RobotMap.mDriveLeftA_ID);
  mDriveLeftB = new DefaultTalonFXDrive(RobotMap.mDriveLeftA_ID);

  mDriveRightMaster = new DefaultTalonFXDrive(RobotMap.mDriveRightA_ID);
  mDriveRightB = new DefaultTalonFXDrive(RobotMap.mDriveLeftB_ID);

  //enabling follower mode for the other  two motors
  mDriveLeftB.set(ControlMode.Follower,RobotMap.mDriveLeftA_ID);

  mDriveRightB.set(ControlMode.Follower,RobotMap.mDriveRightA_ID);

  //creating the differential drive and disabling the saftey
  mDrive = new DifferentialDrive(mDriveLeftMaster, mDriveRightMaster);
  mDrive.setSafetyEnabled(false);
}
//setting specifications for falcons

public void setCoast() {
  mDriveLeftMaster.setNeutralMode(NeutralMode.Coast);
  mDriveLeftB.setNeutralMode(NeutralMode.Coast);
  mDriveRightMaster.setNeutralMode(NeutralMode.Coast);
  mDriveRightB.setNeutralMode(NeutralMode.Coast);
}
public void setBrake() {
  mDriveLeftMaster.setNeutralMode(NeutralMode.Brake);
  mDriveLeftB.setNeutralMode(NeutralMode.Brake);
  mDriveRightMaster.setNeutralMode(NeutralMode.Brake);
  mDriveRightB.setNeutralMode(NeutralMode.Brake);
}

public void EnableVoltComp() {
  mDriveLeftMaster.enableVoltageCompensation(true);
  mDriveLeftB.enableVoltageCompensation(true);
  mDriveRightMaster.enableVoltageCompensation(true);
  mDriveRightB.enableVoltageCompensation(true);
}
public void DisableVoltComp() {
  mDriveLeftMaster.enableVoltageCompensation(false);
  mDriveLeftB.enableVoltageCompensation(false);
  mDriveRightMaster.enableVoltageCompensation(false);
  mDriveRightB.enableVoltageCompensation(false);
}
public void StopDrivetrain() {
  mDriveLeftMaster.set(ControlMode.PercentOutput, 0.0);
  mDriveRightMaster.set(ControlMode.PercentOutput, 0.0);
}

//turning algirithm
public void Curvature(double ThrottleAxis, double TurnAxis) {
  TurnRateCurved = (Constants.kTurnrateCurve*Math.pow(TurnAxis,3)+(1-Constants.kTurnrateCurve)*TurnAxis*Constants.kTurnrateLimit);
  mDrive.curvatureDrive(ThrottleAxis, TurnRateCurved, true);
}

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
