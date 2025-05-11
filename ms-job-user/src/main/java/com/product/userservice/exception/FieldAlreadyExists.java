package com.product.userservice.exception;

public class FieldAlreadyExists extends RuntimeException{
    public FieldAlreadyExists(String message) {
        super(message);
    }
}
