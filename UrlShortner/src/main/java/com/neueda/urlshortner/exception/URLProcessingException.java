package com.neueda.urlshortner.exception;

public class URLProcessingException extends RuntimeException {
    public URLProcessingException(String errorMessage) {
        super(errorMessage);
    }
}
