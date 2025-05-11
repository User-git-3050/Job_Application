package com.product.userservice.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class DetailedUserResponse {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String role;
    private String address;
    private Integer number;
    private CompanyResponse currentCompany;
    private Long connectionCount;
    private List<SkillResponse> skills;
    private List<ExperienceResponse> experiences;

}
