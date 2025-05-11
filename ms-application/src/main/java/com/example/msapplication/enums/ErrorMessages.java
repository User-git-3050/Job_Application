package com.example.msapplication.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessages {
    APPLICATION_NOT_FOUND("Application not found with given id : %s"),
    RESUME_FILE_IS_MISSING("Resume file is missing");

    private final String message;

}
