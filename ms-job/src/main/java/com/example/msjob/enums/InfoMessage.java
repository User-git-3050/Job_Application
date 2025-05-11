package com.example.msjob.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InfoMessage {
    COMPANY_CREATED("Company created successfully "),
    COMPANY_UPDATED("Company updated successfully "),
    JOB_APPROVED("Job approved successfully "),
    JOB_CREATED("Job created successfully "),
    JOB_DELETED("Job deleted successfully "),
    JOB_UPDATED("Job updated successfully "),
    COMPANY_DELETED("Company deleted successfully "),
    NO_PERMISSION("User do not have permission to do this operation");

    private final String message;
}
