package az.msjobauth.mapper;

import az.msjobauth.dao.AdminRegistrationRequest;
import az.msjobauth.dao.OwnerRegistrationRequest;
import az.msjobauth.dao.RecruiterRegistrationRequest;
import az.msjobauth.dao.UserRequest;

public enum UserMapper {
    USER_MAPPER;

    public UserRequest mapOwnertoUserRequest(OwnerRegistrationRequest ownerRegistrationRequest){
        return UserRequest.builder()
                .name(ownerRegistrationRequest.getName())
                .username(ownerRegistrationRequest.getUsername())
                .email(ownerRegistrationRequest.getEmail())
                .password(ownerRegistrationRequest.getPassword())
                .surname(ownerRegistrationRequest.getSurname())
                .address(ownerRegistrationRequest.getAddress())
                .number(ownerRegistrationRequest.getNumber())
                .build();
    }

    public UserRequest mapAdmintoUserRequest(AdminRegistrationRequest adminRegistrationRequest){
        return UserRequest.builder()
                .name(adminRegistrationRequest.getName())
                .username(adminRegistrationRequest.getUsername())
                .email(adminRegistrationRequest.getEmail())
                .password(adminRegistrationRequest.getPassword())
                .surname(adminRegistrationRequest.getSurname())
                .address(adminRegistrationRequest.getAddress())
                .number(adminRegistrationRequest.getNumber())
                .build();
    }

    public UserRequest mapRecruiterToUserRequest(RecruiterRegistrationRequest recruiterRegistrationRequest) {
        return UserRequest.builder()
                .name(recruiterRegistrationRequest.getName())
                .username(recruiterRegistrationRequest.getUsername())
                .email(recruiterRegistrationRequest.getEmail())
                .password(recruiterRegistrationRequest.getPassword())
                .surname(recruiterRegistrationRequest.getSurname())
                .address(recruiterRegistrationRequest.getAddress())
                .number(recruiterRegistrationRequest.getNumber())
                .currentCompanyId(recruiterRegistrationRequest.getCompanyId())
                .build();
    }
}
