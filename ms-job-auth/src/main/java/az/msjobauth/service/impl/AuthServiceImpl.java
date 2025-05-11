package az.msjobauth.service.impl;

import az.msjobauth.client.JobClient;
import az.msjobauth.client.UserClient;
import az.msjobauth.dao.*;
import az.msjobauth.enums.RoleEnum;
import az.msjobauth.exception.AlreadyExistsException;
import az.msjobauth.exception.ForbiddenException;
import az.msjobauth.security.JwtUtil;
import az.msjobauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static az.msjobauth.mapper.UserMapper.*;

@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserClient userClient;
    private final JobClient jobClient;

    @Value("${admin.registration.code}")
    private String adminRegistrationCode;

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager, UserClient userClient, JobClient jobClient) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.userClient = userClient;
        this.jobClient = jobClient;
    }

    @Override
    public JwtResponse generateToken(AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()){
            String token = jwtUtil.generateToken(authentication);
            return JwtResponse.builder().token(token).build();
        }
        else{
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @Override
    public String registerUser(UserRequest userRequest){
        UserResponse existingUser = getUserByEmailOrUsername(userRequest.getEmail(),userRequest.getUsername());

        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        UserResponse userResponse =  userClient.saveUser(userRequest).getBody();

        userClient.setUserRole(userResponse.getId(), RoleEnum.USER);



        return "User registered successfully";

    }

    @Override
    public String registerOwner(OwnerRegistrationRequest ownerRegistrationRequest){
        UserResponse existingUser = getUserByEmailOrUsername(ownerRegistrationRequest.getEmail(),
                ownerRegistrationRequest.getUsername());

        ownerRegistrationRequest.setPassword(passwordEncoder.encode(ownerRegistrationRequest.getPassword()));

        UserResponse userResponse = userClient.saveUser(USER_MAPPER.mapOwnertoUserRequest(ownerRegistrationRequest)).getBody();

        jobClient.createCompany(ownerRegistrationRequest.getCompany(),userResponse.getId());

        userClient.setUserRole(userResponse.getId(), RoleEnum.OWNER);

        return "Owner registered successfully";

    }

    @Override
    public String registerAdmin(AdminRegistrationRequest adminRegistrationRequest){
        UserResponse existingUser = getUserByEmailOrUsername(adminRegistrationRequest.getEmail(),
                adminRegistrationRequest.getUsername());

        if(!adminRegistrationRequest.getAdminCode().equals(adminRegistrationCode)){
            throw new ForbiddenException("Admin code is not valid");
        }

        adminRegistrationRequest.setPassword(passwordEncoder.encode(adminRegistrationRequest.getPassword()));

         UserResponse userResponse = userClient.saveUser(USER_MAPPER.mapAdmintoUserRequest(adminRegistrationRequest)).getBody();

         userClient.setUserRole(userResponse.getId(),RoleEnum.ADMIN);

         return "Admin registered successfully";

    }

    @Override
    public String registerRecruiter(RecruiterRegistrationRequest recruiterRegistrationRequest) {
        UserResponse existingUser = getUserByEmailOrUsername(recruiterRegistrationRequest.getEmail(),
                recruiterRegistrationRequest.getUsername());

        recruiterRegistrationRequest.setPassword(passwordEncoder.encode(recruiterRegistrationRequest.getPassword()));

        UserResponse userResponse = userClient.saveUser(USER_MAPPER.mapRecruiterToUserRequest(recruiterRegistrationRequest)).getBody();

        userClient.setUserRole(userResponse.getId(), RoleEnum.RECRUITER);

        return "Recruiter registered successfully";
    }


    private UserResponse getUserByEmailOrUsername(String email,String username){
        UserResponse existingUser = userClient.getUserByEmailOrUsername(email,username).getBody();
        if(existingUser != null && existingUser.getEmail()!=null && existingUser.getUsername()!=null){
            throw new AlreadyExistsException("User already exists");
        }
        return existingUser;
    }
}
