package com.example.msjob.entity;

import com.example.msjob.enums.ExperienceLevel;
import com.example.msjob.enums.JobType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job")
public class JobEntity {

    @Id
    @SequenceGenerator(sequenceName = "job_id", name = "job_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "job_id")
    private Long id;
    private String title;
    private String description;

    @Enumerated(EnumType.STRING)
    private ExperienceLevel experienceLevel;

    private LocalDate applicationDeadline;
    private Long applicantsCount;
    private String location;
    private Double minSalary;
    private Double maxSalary;

    @Enumerated(EnumType.STRING)
    private JobType type;
    private LocalDateTime postedDate;
    private Long recruiterId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @OneToMany(mappedBy = "job",cascade = CascadeType.ALL)
    private List<JobSkillEntity> jobSkillEntityList;


}
