package com.williams.userexercisesrest.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    private ErrorResponse error = new ErrorResponse();

    @ExceptionHandler(value = {ConstraintViolationException.class,
            NumberFormatException.class,
            DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleConstraint(RuntimeException ex, WebRequest request) {
        error.setError("Unable to process input");
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(value = {NullPointerException.class,
            EmptyResultDataAccessException.class})
    protected ResponseEntity<Object> handleNullPointer(RuntimeException ex, WebRequest request) {
        error.setError("Not found!");
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

}