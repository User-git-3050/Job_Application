package com.product.userservice.dto.request;

import com.product.userservice.enums.EmploymentTypeEnum;
import com.product.userservice.enums.LocationTypeEnum;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class ExperienceRequest {
    private String jobName;
    private Long companyId;
    private String description;
    private EmploymentTypeEnum employmentType;
    private LocationTypeEnum locationType;
    private LocalDate startDate;
    private LocalDate endDate;
}
