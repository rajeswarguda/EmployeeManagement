package com.cognizant.empmgmt.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.empmgmt.dao.ErrorTableDAO;
import com.cognizant.empmgmt.dto.ErrorTableDTO;

@Service
public class ErrorManagerImpl implements ErrorManager {

	@Autowired
	private ErrorTableDAO errorDao;
	private static final Logger LOG = LoggerFactory.getLogger(ErrorManagerImpl.class);

	public int logError(ErrorTableDTO errorDTO) {

		LOG.debug("errorDTO="+errorDTO);
		int noOfRows=errorDao.insert(errorDTO);
		LOG.debug("noOfRows="+noOfRows);
		return noOfRows;
	}



}
