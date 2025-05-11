package com.product.userservice.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchCriteria {
    private String username;
    private String name;
    private String surname;
}
