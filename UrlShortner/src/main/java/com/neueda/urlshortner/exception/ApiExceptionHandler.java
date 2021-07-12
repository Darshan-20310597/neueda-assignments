package com.neueda.urlshortner.exception;

import com.neueda.urlshortner.model.UrlErrorDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {URLValidationException.class})
    public ResponseEntity<UrlErrorDto> handleValidation(RuntimeException ex, WebRequest request) {
        UrlErrorDto errorDto = new UrlErrorDto();
        errorDto.setStatus("400");
        errorDto.setError(ex.getMessage());
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(value = {URLNotFoundException.class})
    public ResponseEntity<UrlErrorDto> handleUrlNotFoundException(RuntimeException ex, WebRequest request) {
        UrlErrorDto errorDto = new UrlErrorDto();
        errorDto.setStatus("404");
        errorDto.setError(ex.getMessage());
        return ResponseEntity.badRequest().body(errorDto);
    }

    @ExceptionHandler(value = {URLProcessingException.class})
    public ResponseEntity<UrlErrorDto> handleServerError(RuntimeException ex, WebRequest request) {
        UrlErrorDto errorDto = new UrlErrorDto();
        errorDto.setStatus("500");
        errorDto.setError(ex.getMessage());
        return ResponseEntity.internalServerError().body(errorDto);
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<UrlErrorDto> handleGenericException(Exception ex, WebRequest request) {
        UrlErrorDto errorDto = new UrlErrorDto();
        errorDto.setStatus("500");
        errorDto.setError(ex.getMessage());
        return ResponseEntity.internalServerError().body(errorDto);
    }

}
