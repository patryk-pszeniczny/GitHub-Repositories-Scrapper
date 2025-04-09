package com.pszeniczny.atiperatask.exceptions.types;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class InvalidAcceptHeaderException extends RuntimeException {

    public InvalidAcceptHeaderException(String message) {
        super(message);
    }
}
