package com.product.userservice.exception;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ErrorResponse {
    private String message;
    private Integer status;
    List<String> errors;
}
