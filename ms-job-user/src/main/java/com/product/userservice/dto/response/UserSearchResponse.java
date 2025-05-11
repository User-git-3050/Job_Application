package com.product.userservice.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserSearchResponse {
    private String username;
    private String name;
    private String surname;
}
