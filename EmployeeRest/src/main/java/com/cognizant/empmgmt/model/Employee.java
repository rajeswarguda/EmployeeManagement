package com.cognizant.empmgmt.model;

import java.util.Calendar;

public class Employee {

    private int empId;
    private String empName;
    private Calendar joiningDate;
    private String department;

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int value) {
        this.empId = value;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String value) {
        this.empName = value;
    }

    public Calendar getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(Calendar value) {
        this.joiningDate = value;
    }

    public String getDepartment() {
        return department;
    }
    public void setDepartment(String value) {
        this.department = value;
    }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeType [empId=");
		builder.append(empId);
		builder.append(", empName=");
		builder.append(empName);
		builder.append(", joiningDate=");
		builder.append(joiningDate);
		builder.append(", department=");
		builder.append(department);
		builder.append("]");
		return builder.toString();
	}
    
    

}
