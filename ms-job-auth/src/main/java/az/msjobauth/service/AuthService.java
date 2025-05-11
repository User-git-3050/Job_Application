package az.msjobauth.service;

import az.msjobauth.dao.*;

public interface AuthService {
    JwtResponse generateToken(AuthRequest authRequest);

    String registerUser(UserRequest userRequest);

    String registerOwner(OwnerRegistrationRequest ownerRegistrationRequest);

    String registerAdmin(AdminRegistrationRequest adminRegistrationRequest);

    String registerRecruiter(RecruiterRegistrationRequest recruiterRegistrationRequest);
}
