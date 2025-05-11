package com.example.msapplication.dao.response;

import com.example.msapplication.enums.StatusEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApplicationResponse {
    StatusEnum status;
    LocalDateTime submissionDate;
    Long user_id;
    Long job_id;

}
