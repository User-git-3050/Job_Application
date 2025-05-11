package com.example.msapplication.event;

import com.example.msapplication.enums.StatusEnum;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ApplicationEvent {
    private Long applicantId;
    private String applicantName;
    private String applicantMail;
    private StatusEnum status;
}
