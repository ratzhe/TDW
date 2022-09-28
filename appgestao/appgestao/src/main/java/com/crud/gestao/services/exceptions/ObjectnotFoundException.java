package com.crud.gestao.services.exceptions;

public class ObjectnotFoundException extends RuntimeException {

	 static final long serialVersionUID = 1L;

	public ObjectnotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectnotFoundException(String message) {
		super(message);
	}
	

}
