package com.oriontek.client.common.handlers;

import com.oriontek.client.command.controller.responses.ClienteCreatedResponse;
import com.oriontek.client.command.controller.responses.DireccionCreatedResponse;
import com.oriontek.client.common.exceptions.ClienteNotFoundException;
import com.oriontek.client.common.exceptions.DireccionNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<Object> handleClienteNotFoundException(ClienteNotFoundException ex) {

        if (ex.getMessage().contains("Dirección")) {
            return new ResponseEntity<>(new DireccionCreatedResponse(null, ex.getMessage()), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ClienteCreatedResponse(null, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DireccionNotFoundException.class)
    public ResponseEntity<DireccionCreatedResponse> handleDireccionNotFoundException(DireccionNotFoundException ex) {
        return new ResponseEntity<>(new DireccionCreatedResponse(null, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        System.err.println("Ocurrió un error inesperado: " + ex.getMessage());
        return new ResponseEntity<>(new ClienteCreatedResponse(null, "Ha ocurrido un error interno del servidor."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
