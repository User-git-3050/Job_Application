package az.msjobauth.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserRequest {
    private String username;
    private String password;
    private String name;
    private String email;
    private String surname;
    private String address;
    private String number;
    private Long currentCompanyId;
}
