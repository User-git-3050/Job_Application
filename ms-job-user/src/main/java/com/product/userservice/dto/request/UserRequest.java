package com.product.userservice.dto.request;

import com.product.userservice.enums.RoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {
    private String name;
    private String surname;

    @NotBlank(message = "Please write username")
    private String username;

    private String password;

    @Email(message = "email should be written in correct format")
    private String email;

    @Enumerated(EnumType.STRING)
    private RoleEnum roleEnum;

    private String address;
    private Integer number;
    private Long currentCompanyId;

}
