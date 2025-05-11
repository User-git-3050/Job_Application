package com.product.userservice.dto.response;

import com.product.userservice.enums.EmploymentTypeEnum;
import com.product.userservice.enums.LocationTypeEnum;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class ExperienceResponse {
    private Long id;
    private Long userProfileId;
    private Long companyId;
    private String description;
    private EmploymentTypeEnum employmentType;
    private LocationTypeEnum locationType;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isCurrent;
}
