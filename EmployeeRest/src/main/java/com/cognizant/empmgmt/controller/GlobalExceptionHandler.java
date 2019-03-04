package com.cognizant.empmgmt.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.UnmarshallingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.xml.sax.SAXParseException;

import com.cognizant.empmgmt.dao.ErrorTableDAO;
import com.cognizant.empmgmt.dto.ErrorTableDTO;
import com.cognizant.empmgmt.model.EmployeeXML;
import com.cognizant.empmgmt.model.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private ErrorTableDAO errorDao;
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	private static final int INVALID_XML_CODE=1000;
	private static final int EMP_UPDATE_NOT_ALLOWED_CODE=1001;
	
	private static final String INVALID_XML="Request XML is invalid";
	private static final String EMP_UPDATE_NOT_ALLOWED="Employee can not be updated bfore 24 hours";
	private static final String SYS_ERROR="Sytem Error";
	private static final String FAILURE="FAILURE";
	
	@ExceptionHandler(org.springframework.oxm.UnmarshallingFailureException.class)
	public ResponseEntity<EmployeeXML> handleException(UnmarshallingFailureException exception, HttpServletRequest request){
		
		LOG.debug("GlobalExceptionHandler: START");
		 
		 exception.printStackTrace();
		
		EmployeeXML empRes = new EmployeeXML();
		Result result = new Result();
		result.setStatus(FAILURE);
		result.setDesc(INVALID_XML);
		empRes.setResult(result);
		
		ErrorTableDTO response = new ErrorTableDTO();
        response.setErrorCode(INVALID_XML_CODE);
        response.setErrorDesc(INVALID_XML);
        
        errorDao.insert(response);
        return new ResponseEntity<EmployeeXML>(empRes, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
	
	@ExceptionHandler(com.cognizant.empmgmt.controller.EmpUpdationException.class)
	public ResponseEntity<EmployeeXML> handleCustomException(EmpUpdationException exception, HttpServletRequest request){
		
		LOG.debug("GlobalExceptionHandler: START");
		 
		 exception.printStackTrace();
		
		EmployeeXML empRes = new EmployeeXML();
		Result result = new Result();
		result.setStatus(FAILURE);
		result.setDesc(EMP_UPDATE_NOT_ALLOWED);
		empRes.setResult(result);
		
		ErrorTableDTO response = new ErrorTableDTO();
        response.setErrorCode(EMP_UPDATE_NOT_ALLOWED_CODE);
        response.setErrorDesc(EMP_UPDATE_NOT_ALLOWED);
        
        errorDao.insert(response);
        return new ResponseEntity<EmployeeXML>(empRes, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<EmployeeXML> handleAllException(Exception exception, HttpServletRequest request){
		
		LOG.debug("GlobalExceptionHandler: START");
		 
		 exception.printStackTrace();
		
		EmployeeXML empRes = new EmployeeXML();
		Result result = new Result();
		result.setStatus(FAILURE);
		result.setDesc(SYS_ERROR);
		empRes.setResult(result);
		
	    return new ResponseEntity<EmployeeXML>(empRes, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
	
}