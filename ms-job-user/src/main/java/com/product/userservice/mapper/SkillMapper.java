package com.product.userservice.mapper;

import com.product.userservice.dto.request.SkillRequest;
import com.product.userservice.dto.response.SkillResponse;
import com.product.userservice.entity.SkillEntity;

public enum SkillMapper {
    SKILL_MAPPER;

    public SkillEntity mapToEntity(SkillRequest skillRequest){
        return SkillEntity.builder()
                .name(skillRequest.getName())
                .description(skillRequest.getDescription())
                .build();
    }

    public SkillResponse mapToResponse(SkillEntity skillEntity){
        return SkillResponse.builder()
                .id(skillEntity.getId())
                .name(skillEntity.getName())
                .description(skillEntity.getDescription())
                .build();
    }
}
