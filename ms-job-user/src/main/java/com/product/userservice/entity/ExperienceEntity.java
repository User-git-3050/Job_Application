package com.product.userservice.entity;

import com.product.userservice.enums.EmploymentTypeEnum;
import com.product.userservice.enums.LocationTypeEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "experience")
public class ExperienceEntity {
    @Id
    @SequenceGenerator(name = "experience_id" , sequenceName = "experience_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "experience_id")
    private Long id;
    private String jobName;
    private Long companyId;
    private String description;

    @Enumerated(EnumType.STRING)
    private EmploymentTypeEnum employmentType;

    @Enumerated(EnumType.STRING)
    private LocationTypeEnum locationType;

    private LocalDate startDate;
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

}
