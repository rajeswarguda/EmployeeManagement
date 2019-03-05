package com.cognizant.empmgmt.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class EmployeeDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int empId;
	private String empName; 
	private Calendar joiningDate;
	private String department;
	private Calendar LastModifidDate;
	
	public EmployeeDTO() {
		super();
	}
	
	public EmployeeDTO(int empId, String empName, Calendar joiningDate, String department, Calendar lastModifidDate) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.joiningDate = joiningDate;
		this.department = department;
		LastModifidDate = lastModifidDate;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public Calendar getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(Calendar joiningDate) {
		this.joiningDate = joiningDate;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Calendar getLastModifidDate() {
		return LastModifidDate;
	}
	public void setLastModifidDate(Calendar lastModifidDate) {
		LastModifidDate = lastModifidDate;
	}

	@Override
	public String toString() {
		Date dt = null;
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeDTO [empId=");
		builder.append(empId);
		builder.append(", empName=");
		builder.append(empName);
		builder.append(", joiningDate=");
		if(joiningDate != null) {
			dt=joiningDate.getTime();
		}
		builder.append(dt);
		builder.append(", department=");
		builder.append(department);
		builder.append(", LastModifidDate=");
		if(LastModifidDate != null) {
			dt=LastModifidDate.getTime();
		}
		builder.append(dt);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
