package az.msjobsearch.dao.request;

import az.msjobsearch.enums.ExperienceLevel;
import az.msjobsearch.enums.JobType;
import lombok.Getter;
import lombok.Setter;

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
