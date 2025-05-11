package az.msjobauth.dao;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String password;
    private String surname;
    private String username;
    private String email;
    private String role;
}
