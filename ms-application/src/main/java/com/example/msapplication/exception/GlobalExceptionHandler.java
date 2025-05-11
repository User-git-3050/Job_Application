package com.example.msapplication.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle(NotFoundException exception ){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(BadRequestException exception){
        return ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
    }

}
