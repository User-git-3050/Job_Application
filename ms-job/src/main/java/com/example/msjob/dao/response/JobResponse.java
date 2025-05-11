package com.example.msjob.dao.response;

import com.example.msjob.enums.ExperienceLevel;
import com.example.msjob.enums.JobType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private LocalDate applicationDeadline;
    private ExperienceLevel experienceLevel;
    private Long applicantsCount;
    private String location;
    private Double minSalary;
    private List<Long> skillIds;
    private Double maxSalary;
    private JobType type;
    private Long recruiterId;
    private LocalDateTime postedDate;
    private Long companyId;
}
