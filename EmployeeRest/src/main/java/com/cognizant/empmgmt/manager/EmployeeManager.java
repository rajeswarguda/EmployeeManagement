package com.cognizant.empmgmt.manager;

import com.cognizant.empmgmt.dto.EmployeeDTO;

public interface EmployeeManager {
	public int createOrUpdateEmployee(EmployeeDTO empDto);
}
