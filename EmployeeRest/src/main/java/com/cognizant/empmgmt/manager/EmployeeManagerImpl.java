package com.cognizant.empmgmt.manager;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.empmgmt.exception.EmpUpdationException;
import com.cognizant.empmgmt.dao.EmployeeDAO;
import com.cognizant.empmgmt.dto.EmployeeDTO;

@Service
public class EmployeeManagerImpl implements EmployeeManager {

	@Autowired
	private EmployeeDAO empDao;
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeManagerImpl.class);

	public int createOrUpdateEmployee(EmployeeDTO empDto) {
		LOG.info("Inside EmployeeManagerImpl");
		LOG.debug("EmployeeManager=" + empDto);

		int noOfRows = 0;
		EmployeeDTO emp = empDao.getEmployee(empDto.getEmpId());

		LOG.debug("EmployeeManager: emp=" + emp);

		if (emp != null) {

			long timeDifInMilliSec = Calendar.getInstance().getTimeInMillis()
					- emp.getLastModifidDate().getTimeInMillis();
			long timeDifMinutes = timeDifInMilliSec / (60 * 1000);

			LOG.debug("timeDifMinutes=" + timeDifMinutes);

			// 1440 minites = 24 hours
			if (timeDifMinutes >= 1440) {
				noOfRows = empDao.update(empDto);
			} else {
				throw new EmpUpdationException("Employee shuold not be updated in 24 hours");
			}

		} else {
			noOfRows = empDao.insert(empDto);
		}
		return noOfRows;
	}

}
