package com.cognizant.empmgmt.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.empmgmt.dao.ErrorTableDAO;
import com.cognizant.empmgmt.dto.ErrorTableDTO;

@Service
public class ErrorManager {

	@Autowired
	private ErrorTableDAO errorDao;
	private static final Logger LOG = LoggerFactory.getLogger(ErrorManager.class);

	public int logError(ErrorTableDTO errorDTO) {

		LOG.debug("ErrorManager="+errorDTO);

		int noOfRows=errorDao.insert(errorDTO);

		return noOfRows;
	}



}
