package com.example.msapplication.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InfoMessages {
    APPLICATION_CREATED("Application created successfully"),
    APPLICATION_STATUS_UPDATED("Application status updated successfully"),
    RESUME_UPDATED("Resume updated successfully"),
    APPLICATION_DELETED("Application deleted successfully");

    private final String message;
}
