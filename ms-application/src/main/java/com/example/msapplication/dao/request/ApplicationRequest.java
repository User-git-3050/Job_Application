package com.example.msapplication.dao.request;

import com.example.msapplication.enums.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApplicationRequest {
    StatusEnum status;
    LocalDateTime submissionDate;
    Long job_id;

}
