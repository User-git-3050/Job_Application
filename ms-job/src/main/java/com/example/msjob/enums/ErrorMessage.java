package com.example.msjob.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorMessage {
    JOB_NOT_FOUND("Job not found with given id : %s"),
    COMPANY_NOT_FOUND_WITH_ID("Company not found with given id : %s"),
    COMPANY_NOT_FOUND("Company not found");

    private final String message;
}
