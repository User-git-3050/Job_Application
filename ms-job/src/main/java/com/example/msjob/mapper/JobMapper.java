package com.example.msjob.mapper;


import com.example.msjob.dao.request.JobRequest;
import com.example.msjob.dao.response.JobResponse;
import com.example.msjob.entity.CompanyEntity;
import com.example.msjob.entity.JobEntity;
import com.example.msjob.entity.JobSkillEntity;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.msjob.mapper.JobSkillMapper.*;

public enum JobMapper {
    JOB_MAPPER;

    public JobEntity mapToEntity(JobRequest jobRequest, CompanyEntity companyEntity, Long recruiterId) {
        return JobEntity.builder()
                .title(jobRequest.getTitle())
                .description(jobRequest.getDescription())
                .location(jobRequest.getLocation())
                .maxSalary(jobRequest.getMaxSalary())
                .minSalary(jobRequest.getMinSalary())
                .type(jobRequest.getType())
                .postedDate(LocalDateTime.now())
                .applicationDeadline(jobRequest.getApplicationDeadline())
                .applicantsCount(0L)
                .experienceLevel(jobRequest.getExperienceLevel())
                .company(companyEntity)
                .recruiterId(recruiterId)
                .build();
    }

    public JobResponse mapToResponse(JobEntity jobEntity) {
        return JobResponse.builder()
                .id(jobEntity.getId())
                .title(jobEntity.getTitle())
                .description(jobEntity.getDescription())
                .applicationDeadline(jobEntity.getApplicationDeadline())
                .experienceLevel(jobEntity.getExperienceLevel())
                .location(jobEntity.getLocation())
                .minSalary(jobEntity.getMinSalary())
                .maxSalary(jobEntity.getMaxSalary())
                .postedDate(jobEntity.getPostedDate())
                .type(jobEntity.getType())
                .postedDate(jobEntity.getPostedDate())
                .companyId(jobEntity.getCompany().getId())
                .applicantsCount(jobEntity.getApplicantsCount())
                .recruiterId(jobEntity.getRecruiterId())
                .build();
    }


}
