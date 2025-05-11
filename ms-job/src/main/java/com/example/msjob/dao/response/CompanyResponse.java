package com.example.msjob.dao.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResponse {
    private Long id;
    private String name;
    private String about;
    private String details;
    private String address;
}
