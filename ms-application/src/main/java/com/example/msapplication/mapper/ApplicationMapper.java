package com.example.msapplication.mapper;

import com.example.msapplication.entity.ApplicationEntity;
import com.example.msapplication.enums.StatusEnum;
import com.example.msapplication.dao.response.ApplicationResponse;

public enum ApplicationMapper {
    APPLICATION_MAPPER;

    public ApplicationResponse toResponse(ApplicationEntity applicationEntity) {
        return ApplicationResponse.builder()
                .status(applicationEntity.getStatus())
                .job_id(applicationEntity.getJobId())
                .user_id(applicationEntity.getUserId())
                .submissionDate(applicationEntity.getSubmissionDate())
                .build();
    }

    public ApplicationEntity toEntity(Long jobId,Long userId,String resumePath) {
        return ApplicationEntity.builder()
                .status(StatusEnum.PENDING)
                .jobId(jobId)
                .resumePath(resumePath)
                .userId(userId)
                .build();

    }
}
