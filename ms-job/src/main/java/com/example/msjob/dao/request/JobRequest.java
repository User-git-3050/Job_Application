package com.example.msjob.dao.request;

import com.example.msjob.enums.ExperienceLevel;
import com.example.msjob.enums.JobType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
public class JobRequest {
    private String title;
    private String description;
    private String location;
    private Long salary;
    private JobType type;
    private LocalDate applicationDeadline;
    private ExperienceLevel experienceLevel;
    private Double minSalary;
    private Double maxSalary;
    private List<Long> skillIds;
}
