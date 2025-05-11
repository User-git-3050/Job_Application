package com.example.msjob.dao.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyUserResponse {
    private Long userId;
    private Long companyId;
}
