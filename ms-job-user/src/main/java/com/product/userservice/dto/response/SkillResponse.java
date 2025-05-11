package com.product.userservice.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SkillResponse {
    private Long id;
    private String name;
    private String description;
}
