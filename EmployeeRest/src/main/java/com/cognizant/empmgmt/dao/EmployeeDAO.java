package com.cognizant.empmgmt.dao;

import java.util.List;

import com.cognizant.empmgmt.dto.EmployeeDTO;

public interface EmployeeDAO {

	public int insert(EmployeeDTO empdto);
	public int update(EmployeeDTO empdto);
	public EmployeeDTO getEmployee(int empId);
	public List<EmployeeDTO> getAllEmployees(); 

}
