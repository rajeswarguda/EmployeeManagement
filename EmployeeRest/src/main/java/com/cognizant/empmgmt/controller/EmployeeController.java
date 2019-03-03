package com.cognizant.empmgmt.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.empmgmt.dto.EmployeeDTO;
import com.cognizant.empmgmt.manager.EmployeeManager;
import com.cognizant.empmgmt.model.EmployeeRequest;
import com.cognizant.empmgmt.model.EmployeeResponse;

@RestController
@RequestMapping("EmpService")
public class EmployeeController {
	
	@Autowired
	private EmployeeManager manager;
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

	@RequestMapping(value="/emp/create", method=RequestMethod.POST,consumes={"application/xml"})
	@ResponseBody
	public ResponseEntity<EmployeeResponse> createOrUpdateEmployee(@RequestBody EmployeeRequest empRequest,HttpServletResponse response) {
		
		LOG.debug("empRequest="+empRequest);
		
		EmployeeDTO empDto = new EmployeeDTO();
		empDto.setEmpId(empRequest.getEmpId());
		empDto.setEmpName(empRequest.getEmpName());
		empDto.setJoiningDate(empRequest.getJoiningDate());
		empDto.setDepartment(empRequest.getDepartment());
		
		LOG.debug("createOrUpdateEmployee: empRequest="+empRequest);
		
		int rows = manager.createOrUpdateEmployee(empDto);
		EmployeeResponse empResponse = new EmployeeResponse();
		if (rows > 0) {
			empResponse.setStatus("SUCCESS");
			empResponse.setDesc("Employee is added/updated successfully");
		} else {
			empResponse.setStatus("FAILURE");
			empResponse.setDesc("Employee is not added/updated successfully");
		}
		
		return new ResponseEntity<EmployeeResponse>(empResponse, HttpStatus.ACCEPTED);
		
	}
	
}
