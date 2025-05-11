package com.example.msapplication.dao.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String role;

}
