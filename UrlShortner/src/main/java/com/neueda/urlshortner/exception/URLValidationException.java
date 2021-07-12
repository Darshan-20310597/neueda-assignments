package com.neueda.urlshortner.exception;

public class URLValidationException extends RuntimeException {
    public URLValidationException(String message) {
        super(message);
    }
}
