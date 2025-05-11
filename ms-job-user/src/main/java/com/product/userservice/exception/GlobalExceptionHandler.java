package com.product.userservice.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.ArrayList;
import jakarta.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handleNotFoundException(HttpServletRequest request,NotFoundException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(NOT_FOUND.value())
                .build();
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ErrorResponse handle(HttpServletRequest request,UnauthorizedException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(UNAUTHORIZED.value())
                .build();
    }

    @ExceptionHandler(FieldAlreadyExists.class)
    @ResponseStatus(CONFLICT)
    public ErrorResponse handle(HttpServletRequest request,FieldAlreadyExists e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(CONFLICT.value())
                .build();
    }

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handle(HttpServletRequest request,AlreadyExistsException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(BAD_REQUEST.value())
                .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(HttpServletRequest request, MethodArgumentNotValidException e) {
        ArrayList<String> errors = new ArrayList<>();
        e.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));

        return ErrorResponse.builder()
                .message("Validation error happened")
                .status(BAD_REQUEST.value())
                .errors(errors)
                .build();
    }
}
