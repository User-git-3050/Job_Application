package com.product.userservice.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyUserResponse {
    private Long userId;
    private Long companyId;
}
