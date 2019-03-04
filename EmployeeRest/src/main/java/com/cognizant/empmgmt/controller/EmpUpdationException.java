package com.cognizant.empmgmt.controller;

public class EmpUpdationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmpUpdationException() {
	}

	public EmpUpdationException(String message) {
		super(message);
	}

	public EmpUpdationException(Throwable cause) {
		super(cause);
	}

	public EmpUpdationException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmpUpdationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
