package com.neueda.atmmachine.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends FatalException{
	
	 private static final long serialVersionUID = 1L;
	 private Errors errors;
	    
	    public BadRequestException() {
	        super();
	    }

	    public BadRequestException(String message) {
	        super("Bad Request", message);
	    }

	    public BadRequestException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public BadRequestException(String message, Errors errors) {
	        super("Bad Request", message);
	        this.errors = errors;
	    }

	    public Errors getErrors() {
	        return errors;
	    }

}
