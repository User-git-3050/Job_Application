package az.msjobauth.dao;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CompanyRequest {
    private String name;
    private String about;
    private String details;
    private String address;
}

