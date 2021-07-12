package com.neueda.atmmachine.exceptions;

public class AccountsException extends Exception{
	private static final long serialVersionUID = -470180507998010368L;

	public AccountsException() {
		super();
	}

	public AccountsException(final String message) {
		super("Bad Input Exception. Please check the Values Entered");
	}

}
