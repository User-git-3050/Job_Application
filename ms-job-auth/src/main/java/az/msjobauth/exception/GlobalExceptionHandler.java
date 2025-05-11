package az.msjobauth.exception;

import az.msjobauth.dao.ErrorResponse;
import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handle(AlreadyExistsException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorResponse handle(ForbiddenException ex) {
        return ErrorResponse.builder()
                .message(ex.getMessage())
                .build();
    }
}
