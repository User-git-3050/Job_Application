package az.msjobsearch.dao.response;

import az.msjobsearch.enums.ExperienceLevel;
import az.msjobsearch.enums.JobType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
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
