package com.crud.gestao.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException {

	 static final long serialVersionUID = 1L;

	public DataIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegrityViolationException(String message) {
		super(message);
	}
	

}
