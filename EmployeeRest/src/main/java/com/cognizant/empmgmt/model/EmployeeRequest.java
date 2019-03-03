package com.cognizant.empmgmt.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Employee")

public class EmployeeRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int empId;
	private String empName;
	private Calendar joiningDate;
	private String department;
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

	@Override
	public String toString() {
		Date dt = null;
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeRequest [empId=");
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
		builder.append("]");
		return builder.toString();
	}
	
}
