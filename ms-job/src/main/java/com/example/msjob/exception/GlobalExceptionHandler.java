package com.example.msjob.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ErrorResponse handle (HttpServletRequest request, NotFoundException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(NOT_FOUND.value())
                .path(request.getServletPath())
                .timestamp(LocalDateTime.now())
                .build();

    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ErrorResponse handle (HttpServletRequest request, UnauthorizedException e) {
        return ErrorResponse.builder()
                .message(e.getMessage())
                .status(UNAUTHORIZED.value())
                .path(request.getServletPath())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
