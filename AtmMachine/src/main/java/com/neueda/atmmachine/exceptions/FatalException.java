package com.neueda.atmmachine.exceptions;

public class FatalException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
    private String error;
    private String description;


    public FatalException()
    {
        super();
    }

    public FatalException( String error, String description )
    {
        super( description );
        this.error = error;
        this.description = description;
    }


    public FatalException( String message, Throwable cause )
    {
        super( message, cause );
    }

    public String getError() {

        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
