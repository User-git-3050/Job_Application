package com.product.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    USER_NOT_FOUND_WITH_NAME("User not found with given username: %s"),
    USER_NOT_FOUND_WITH_EMAIL("User not found with given email: %s"),
    USER_NOT_FOUND_WITH_ID("User not found with given id: %s"),
    USER_EXISTS_WITH_USERNAME("User with given username: %s already exists"),
    CONNECTION_ALREADY_EXISTS("Connection already exists"),
    USER_EXISTS_WITH_EMAIL("User with given email: %s already exists"),
    SKILL_EXISTS_WITH_NAME("Skill with given name: %s already exists"),
    EXPERIENCE_NOT_FOUND_WITH_ID("Experience not found with given id: %s"),
    COMPANY_NOT_FOUND_FOR_USER("Company not found for this user: %s"),
    NO_PERMISSION("User do not have permission to do this operation"),
    CONNECTION_REQUEST_NOT_FOUND_WITH_ID("Connection request not found with given id: %s"),
    SKILL_NOT_FOUND_WITH_ID("Skill not found with given id: %s");

    private final String message;
}
