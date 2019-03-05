package com.cognizant.empmgmt.dto;

import java.io.Serializable;

public class ErrorTableDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int errorCode;
	private String errorDesc; 
	private String requestXML;
	
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorDesc() {
		return errorDesc;
	}
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	public String getRequestXML() {
		return requestXML;
	}
	public void setRequestXML(String requestXML) {
		this.requestXML = requestXML;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorTableDTO [errorCode=");
		builder.append(errorCode);
		builder.append(", errorDesc=");
		builder.append(errorDesc);
		builder.append(", requestXML=");
		builder.append(requestXML);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
