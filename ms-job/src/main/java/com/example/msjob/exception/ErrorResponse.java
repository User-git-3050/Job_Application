package com.example.msjob.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ErrorResponse {
    private String message;
    private Integer status;
    private String path;
    private LocalDateTime timestamp;
}
