package com.product.userservice.mapper;

import com.product.userservice.dto.request.UserRequest;
import com.product.userservice.dto.response.CompanyResponse;
import com.product.userservice.dto.response.DetailedUserResponse;
import com.product.userservice.dto.response.UserResponse;
import com.product.userservice.dto.response.UserSearchResponse;
import com.product.userservice.entity.ExperienceEntity;
import com.product.userservice.entity.SkillEntity;
import com.product.userservice.entity.UserEntity;
import com.product.userservice.entity.UserProfile;

import static com.product.userservice.mapper.ExperienceMapper.EXPERIENCE_MAPPER;
import static com.product.userservice.mapper.SkillMapper.*;

public enum UserEntityMapper {
    USER_ENTITY_MAPPER;

    public UserEntity toUserEntity(UserRequest userRequest) {
        return UserEntity.builder()
                .name(userRequest.getName())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .surname(userRequest.getSurname())
                .username(userRequest.getUsername())
                .connectionCount(0L)
                .roleEnum(userRequest.getRoleEnum())
                .userProfile(UserProfile.builder()
                        .address(userRequest.getAddress())
                        .number(userRequest.getNumber())
                        .currentCompanyId(userRequest.getCurrentCompanyId())
                        .build())
                .build();
    }

    public UserResponse toUserResponse(UserEntity userEntity) {
        return UserResponse.builder()
                .id(userEntity.getId())
                .password(userEntity.getPassword())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .surname(userEntity.getSurname())
                .username(userEntity.getUsername())
                .role(String.valueOf(userEntity.getRoleEnum()))
                .build();
}
    public DetailedUserResponse toDetailedUserResponse(UserEntity userEntity, CompanyResponse companyResponse) {
        UserProfile userProfile = userEntity.getUserProfile();

        return DetailedUserResponse.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .username(userEntity.getUsername())
                .role(String.valueOf(userEntity.getRoleEnum()))
                .address(userProfile.getAddress())
                .number(userProfile.getNumber())
                .currentCompany(companyResponse)
                .connectionCount(userEntity.getConnectionCount())
                .skills(userProfile.getSkills().stream()
                        .map(SKILL_MAPPER::mapToResponse)
                        .toList())
                .experiences(userProfile.getExperiences().stream()
                        .map(EXPERIENCE_MAPPER::mapToResponse)
                        .toList())
                .build();
    }

    public UserSearchResponse mapToUserSearchResponse(UserEntity userEntity) {
        return UserSearchResponse.builder()
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .username(userEntity.getUsername())
                .build();
    }

}
