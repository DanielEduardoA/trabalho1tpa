package br.edu.univas.api.marvel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice()
public class MarvelApiExceptionHandler {
    
    @ExceptionHandler(MarvelApiException.class)
    protected ResponseEntity<Object> handleEntity(MarvelApiException ex) {
        return getErrorResponse(ex.getMessage(), ex.getHttpStatus());
    }
    
    private ResponseEntity<Object> getErrorResponse(String message, HttpStatus httpStatus) {
        return ResponseEntity
                .status(httpStatus)
                .body(message);
    }

}
