package com.oriontek.client.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DireccionNotFoundException extends RuntimeException {
    public DireccionNotFoundException(String message) {
        super(message);
    }
    public DireccionNotFoundException(String message, Throwable cause) {super(message, cause);}
}
