package com.product.userservice.mapper;

import com.product.userservice.dto.request.ExperienceRequest;
import com.product.userservice.dto.response.ExperienceResponse;
import com.product.userservice.entity.ExperienceEntity;
import com.product.userservice.entity.UserProfile;

public enum ExperienceMapper {
    EXPERIENCE_MAPPER;

    public ExperienceEntity mapToEntity(ExperienceRequest experienceRequest, UserProfile userProfile) {
        return ExperienceEntity.builder()
                .companyId(experienceRequest.getCompanyId())
                .userProfile(userProfile)
                .jobName(experienceRequest.getJobName())
                .locationType(experienceRequest.getLocationType())
                .employmentType(experienceRequest.getEmploymentType())
                .description(experienceRequest.getDescription())
                .startDate(experienceRequest.getStartDate())
                .endDate(experienceRequest.getEndDate())
                .build();

    }

    public ExperienceResponse mapToResponse(ExperienceEntity experienceEntity) {
        return ExperienceResponse.builder()
                .id(experienceEntity.getId())
                .companyId(experienceEntity.getCompanyId())
                .userProfileId(experienceEntity.getUserProfile().getId())
                .description(experienceEntity.getDescription())
                .employmentType(experienceEntity.getEmploymentType())
                .locationType(experienceEntity.getLocationType())
                .startDate(experienceEntity.getStartDate())
                .endDate(experienceEntity.getEndDate())
                .build();

    }
}
