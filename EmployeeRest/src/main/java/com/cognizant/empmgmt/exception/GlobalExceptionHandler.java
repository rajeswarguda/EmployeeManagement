package com.cognizant.empmgmt.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.UnmarshallingFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.cognizant.empmgmt.dto.ErrorTableDTO;
import com.cognizant.empmgmt.manager.ErrorManager;
import com.cognizant.empmgmt.model.EmployeeXML;
import com.cognizant.empmgmt.model.Result;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@Autowired
	private ErrorManager errorManager;
	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	private static final int INVALID_XML_CODE = 1000;
	private static final int EMP_UPDATE_NOT_ALLOWED_CODE = 1001;

	private static final String INVALID_XML = "Payload is invalid";
	private static final String EMP_UPDATE_NOT_ALLOWED = "Employee can not be updated before 24 hours";
	private static final String SYS_ERROR = "Sytem Error";
	private static final String PAYLOAD_RESTRICTED = "Payload is Restricted";
	private static final String FAILURE = "FAILURE";

	@ExceptionHandler(org.springframework.oxm.UnmarshallingFailureException.class)
	public ResponseEntity<EmployeeXML> handleException(UnmarshallingFailureException exception, WebRequest webRequest) {

		LOG.debug("GlobalExceptionHandler: START");

		exception.printStackTrace();

		String requestXML = (String) webRequest.getAttribute("ReqPayload", RequestAttributes.SCOPE_REQUEST);
		LOG.debug("GlobalExceptionHandler: requestXML=" + requestXML);

		EmployeeXML empRes = new EmployeeXML();
		Result result = new Result();
		result.setStatus(FAILURE);
		result.setDesc(INVALID_XML);
		empRes.setResult(result);

		ErrorTableDTO response = new ErrorTableDTO();
		response.setErrorCode(INVALID_XML_CODE);
		response.setErrorDesc(INVALID_XML);
		response.setRequestXML(requestXML);

		errorManager.logError(response);
		return new ResponseEntity<EmployeeXML>(empRes, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler(com.cognizant.empmgmt.exception.EmpUpdationException.class)
	public ResponseEntity<EmployeeXML> handleCustomException(EmpUpdationException exception, WebRequest webRequest) {

		LOG.debug("GlobalExceptionHandler: START");

		exception.printStackTrace();

		String requestXML = (String) webRequest.getAttribute("ReqPayload", RequestAttributes.SCOPE_REQUEST);
		LOG.debug("GlobalExceptionHandler: requestXML=" + requestXML);

		EmployeeXML empRes = new EmployeeXML();
		Result result = new Result();
		result.setStatus(FAILURE);
		result.setDesc(EMP_UPDATE_NOT_ALLOWED);
		empRes.setResult(result);

		ErrorTableDTO response = new ErrorTableDTO();
		response.setErrorCode(EMP_UPDATE_NOT_ALLOWED_CODE);
		response.setErrorDesc(PAYLOAD_RESTRICTED);
		response.setRequestXML(requestXML);

		errorManager.logError(response);
		return new ResponseEntity<EmployeeXML>(empRes, HttpStatus.FORBIDDEN);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<EmployeeXML> handleAllException(Exception exception, WebRequest webRequest) {

		LOG.debug("GlobalExceptionHandler: START");

		exception.printStackTrace();

		String requestXML = (String) webRequest.getAttribute("ReqPayload", RequestAttributes.SCOPE_REQUEST);
		LOG.debug("GlobalExceptionHandler: requestXML=" + requestXML);

		EmployeeXML empRes = new EmployeeXML();
		Result result = new Result();
		result.setStatus(FAILURE);
		result.setDesc(SYS_ERROR);
		empRes.setResult(result);

		return new ResponseEntity<EmployeeXML>(empRes, HttpStatus.INTERNAL_SERVER_ERROR);

	}

}