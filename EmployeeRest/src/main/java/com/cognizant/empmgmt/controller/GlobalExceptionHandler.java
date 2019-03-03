package com.cognizant.empmgmt.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cognizant.empmgmt.dao.ErrorTableDAO;
import com.cognizant.empmgmt.dto.ErrorTableDTO;
import com.cognizant.empmgmt.model.EmployeeResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private ErrorTableDAO errorDao;
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<EmployeeResponse> exception(Exception exception, HttpServletRequest request){
		
		LOG.debug("GlobalExceptionHandler: START");
		 
		 exception.printStackTrace();
		int errorCode=123;
		String errorDesc = exception.getMessage();
		
		EmployeeResponse empRes = new EmployeeResponse();
		empRes.setStatus("FAILURE");
		empRes.setDesc(errorDesc);
		
		ErrorTableDTO response = new ErrorTableDTO();
        response.setErrorCode(errorCode);
        response.setErrorDesc(errorDesc);
        
        errorDao.insert(response);
        return new ResponseEntity<EmployeeResponse>(empRes, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
	
}