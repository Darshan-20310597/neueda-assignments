package com.neueda.atmmachine.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.neueda.atmmachine.exceptions.BadRequestException;
import com.neueda.atmmachine.model.ExceptionResponse;

@ControllerAdvice
public class ErrorControllerAdvice {
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFound(final HttpMessageNotReadableException exception,
			final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage("Bad Input Exception. Please check the Values Entered");
		error.callerURL(request.getRequestURI());

		return error;
	}
	
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleResourceNotFound(final BadRequestException exception,
			final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage("Invalid Account Number or Pin. Please check and Retry");
		error.callerURL(request.getRequestURI());
		return error;
	}
	

}
