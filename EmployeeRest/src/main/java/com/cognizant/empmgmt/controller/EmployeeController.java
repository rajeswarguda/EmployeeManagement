package com.cognizant.empmgmt.controller;

import java.io.StringReader;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import com.cognizant.empmgmt.dto.EmployeeDTO;
import com.cognizant.empmgmt.manager.EmployeeManager;
import com.cognizant.empmgmt.model.EmployeeXML;
import com.cognizant.empmgmt.model.Result;

@RestController
@RequestMapping("EmpService")
public class EmployeeController {

	@Autowired
	private EmployeeManager manager;
	@Autowired
	private Jaxb2Marshaller jaxb2Mashaller;

	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

	@RequestMapping(value="/emp/create", method=RequestMethod.POST,consumes={"application/xml"})
	@ResponseBody
	public ResponseEntity<EmployeeXML> createOrUpdateEmployee(@RequestBody String body,HttpServletResponse response, WebRequest webRequest) {

		webRequest.setAttribute("ReqPayload", body, RequestAttributes.SCOPE_REQUEST);

		Source source = new StreamSource(new StringReader(body));
		EmployeeXML empRequest = (EmployeeXML) jaxb2Mashaller.unmarshal(source);

		LOG.debug("empRequest="+empRequest);

		EmployeeDTO empDto = new EmployeeDTO();
		empDto.setEmpId(empRequest.getEmployee().getEmpId());
		empDto.setEmpName(empRequest.getEmployee().getEmpName());
		empDto.setJoiningDate(empRequest.getEmployee().getJoiningDate());
		empDto.setDepartment(empRequest.getEmployee().getDepartment());

		LOG.debug("createOrUpdateEmployee: empRequest="+empRequest);

		int rows = manager.createOrUpdateEmployee(empDto);
		EmployeeXML empResponse = new EmployeeXML();
		Result result = new Result();
		if (rows > 0) {
			result.setStatus("SUCCESS");
			result.setDesc("Employee is added/updated successfully");
		} else {
			result.setStatus("FAILURE");
			result.setDesc("Employee is not added/updated successfully");
		}

		empResponse.setResult(result);

		return new ResponseEntity<EmployeeXML>(empResponse, HttpStatus.ACCEPTED);

	}

}
