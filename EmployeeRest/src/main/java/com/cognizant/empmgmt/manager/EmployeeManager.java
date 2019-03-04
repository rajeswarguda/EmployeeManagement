package com.cognizant.empmgmt.manager;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.empmgmt.controller.EmpUpdationException;
import com.cognizant.empmgmt.dao.EmployeeDAO;
import com.cognizant.empmgmt.dto.EmployeeDTO;

@Service
public class EmployeeManager {
	
	@Autowired
	private EmployeeDAO empDao;
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeManager.class);
	
	public int createOrUpdateEmployee(EmployeeDTO empDto) {
		
		LOG.debug("EmployeeManager="+empDto);
		int noOfRows=0;
		EmployeeDTO emp = empDao.getEmployee(empDto.getEmpId());
		
		
		LOG.debug("EmployeeManager: emp="+emp);
		
		if (emp!=null) {
			
			long timeDifInMilliSec  = Calendar.getInstance().getTimeInMillis()-emp.getLastModifidDate().getTimeInMillis();
			long timeDifHours = timeDifInMilliSec / (60 * 60 * 1000);
			
			LOG.debug("timeDifHours="+timeDifHours);
			
			if(timeDifHours >=24) {
				noOfRows=empDao.update(empDto);
			}else {
				throw new EmpUpdationException("Employee shuold not be updated in 24 hours");
			}
			
		} else {
			noOfRows=empDao.insert(empDto);
		}
		return noOfRows;
	}
	
	

}
