package com.product.userservice.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SkillRequest {
    @NotBlank(message = "Skill name should be written")
    private String name;

    private String description;
}
