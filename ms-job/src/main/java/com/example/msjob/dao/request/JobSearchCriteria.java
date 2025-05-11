package com.example.msjob.dao.request;

import com.example.msjob.enums.ExperienceLevel;
import com.example.msjob.enums.JobType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class JobSearchCriteria {
    private String title;
    private String description;
    private ExperienceLevel experienceLevel;
    private LocalDate applicationDeadLine;
    private String location;
    private Double minSalary;
    private Double maxSalary;
    private JobType jobType;
    private LocalDateTime postedDate;
    private String company;
}