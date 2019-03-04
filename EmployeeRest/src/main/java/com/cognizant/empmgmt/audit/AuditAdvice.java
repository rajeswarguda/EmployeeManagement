package com.cognizant.empmgmt.audit;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AuditAdvice {
	
	private static final Logger LOG = LoggerFactory.getLogger(AuditAdvice.class);

	@Around("execution(* com.cognizant.empmgmt..*.*(..))")  
	public Object myadvice(ProceedingJoinPoint pjp) throws Throwable   
	{  
		LOG.debug("AuditAdvice: START");

		String signature = pjp.getSignature().toString();

		long start = System.currentTimeMillis();
		Object obj=  pjp.proceed(); 
		long executionTime = System.currentTimeMillis() - start;
		LOG.debug(signature +" method executionTime="+executionTime);

		if(signature.equals("ResponseEntity com.cognizant.empmgmt.controller.EmployeeController.createOrUpdateEmployee(String,HttpServletResponse)")) {
			HttpServletResponse res = (HttpServletResponse) pjp.getArgs()[1];
			res.addHeader("ExecutionTime", executionTime+"");
		}

		return obj;  
	}  

}
