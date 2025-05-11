package az.msjobauth.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OwnerRegistrationRequest {
    private String username;
    private String password;
    private String name;
    private String email;
    private String surname;
    private String address;
    private String number;
    private CompanyRequest company;
}
