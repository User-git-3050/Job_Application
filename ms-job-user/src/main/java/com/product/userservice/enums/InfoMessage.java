package com.product.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum InfoMessage {
    USER_SAVED("User saved successfully"),
    USER_ROLE_WAS_SET("User role was set successfully"),
    USER_DETAILS_UPDATED("User details updated successfully"),
    SKILLS_ADDED_TO_USER("Skills added to user successfully"),
    SKILL_CREATED("Skill created successfully"),
    SKILL_UPDATED("Skill edited successfully"),
    SKILL_DELETED("Skill deleted successfully"),
    EXPERIENCE_ADDED_TO_USER("Experience added to user successfully"),
    EXPERIENCE_UPDATED("Experience edited successfully"),
    EXPERIENCE_DELETED("Experience deleted successfully"),
    CONNECTION_REQUEST_SENT("Connection request sent successfully"),
    CONNECTION_REQUEST_ACCEPTED("Connection request accepted successfully"),
    CONNECTION_REQUEST_REJECTED("Connection request rejected successfully"),
    CONNECTION_DELETED("Connection deleted successfully"),
    USER_DELETED("User deleted successfully");


    private final String message;
}
