package com.cognizant.empmgmt.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "EMPXML")
public class EmployeeXML {

    private Employee employee;
    private Result result;
    
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Result getResult() {
		return result;
	}
	public void setResult(Result result) {
		this.result = result;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EmployeeXML [employee=");
		builder.append(employee);
		builder.append(", result=");
		builder.append(result);
		builder.append("]");
		return builder.toString();
	}
	
    

  
}
