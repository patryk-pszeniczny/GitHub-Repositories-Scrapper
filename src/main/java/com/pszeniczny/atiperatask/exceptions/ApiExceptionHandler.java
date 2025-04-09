package com.pszeniczny.atiperatask.exceptions;

import com.pszeniczny.atiperatask.exceptions.types.InvalidAcceptHeaderException;
import com.pszeniczny.atiperatask.exceptions.types.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new ErrorResponse(404, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidAcceptHeaderException.class)
    public ResponseEntity<ErrorResponse> handleInvalidAcceptHeaderException(InvalidAcceptHeaderException ex) {
        return new ResponseEntity<>(new ErrorResponse(406, ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
    }
}
