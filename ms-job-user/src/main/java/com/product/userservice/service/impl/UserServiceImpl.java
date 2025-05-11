package com.product.userservice.service.impl;

import com.product.userservice.client.JobClient;
import com.product.userservice.dto.request.UserRequest;
import com.product.userservice.dto.request.UserSearchCriteria;
import com.product.userservice.dto.response.*;
import com.product.userservice.entity.ExperienceEntity;
import com.product.userservice.entity.SkillEntity;
import com.product.userservice.entity.UserEntity;
import com.product.userservice.entity.UserProfile;
import com.product.userservice.enums.RoleEnum;
import com.product.userservice.exception.FieldAlreadyExists;
import com.product.userservice.exception.NotFoundException;
import com.product.userservice.exception.UnauthorizedException;
import com.product.userservice.mapper.UserEntityMapper;
import com.product.userservice.repository.SkillRepository;
import com.product.userservice.repository.UserProfileRepository;
import com.product.userservice.repository.UserRepository;
import com.product.userservice.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.product.userservice.enums.ErrorMessage.*;
import static com.product.userservice.enums.InfoMessage.*;
import static com.product.userservice.mapper.UserEntityMapper.*;
import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;
    private final JobClient jobClient;
    private final UserProfileRepository userProfileRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, SkillRepository skillRepository, JobClient jobClient, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
        this.jobClient = jobClient;
        this.userProfileRepository = userProfileRepository;
    }

    @Override
    public UserResponse findByUsername(String username) {
        return USER_ENTITY_MAPPER.toUserResponse(getUserEntityByUsername(username));
    }

    @Override
    public UserResponse findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(USER_ENTITY_MAPPER::toUserResponse)
                .orElseThrow(()->new NotFoundException(
                        format(
                                USER_NOT_FOUND_WITH_EMAIL.getMessage(),
                                email
                        )
                ));
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        UserEntity userEntity =  userRepository.save(USER_ENTITY_MAPPER.toUserEntity(userRequest));
        return USER_ENTITY_MAPPER.toUserResponse(userEntity);

    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(USER_ENTITY_MAPPER::toUserResponse).toList();
    }

    @Override
    public String setUserRole(Long userId, RoleEnum roleEnum) {
        UserEntity userEntity = getUserEntityById(userId);
        userEntity.setRoleEnum(roleEnum);
        userRepository.save(userEntity);
        return USER_ROLE_WAS_SET.getMessage();
    }

    @Override
    public Long findUserIdByUsername(String username) {
        return getUserEntityByUsername(username).getId();
    }

    @Override
    public Boolean isUserExist(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public String editUserDetails(UserRequest userRequest,String username) {
        UserEntity userEntity = getUserEntityByUsername(username);

        UserProfile userProfile = userEntity.getUserProfile();
        userProfile.setAddress(userRequest.getAddress());
        userProfile.setNumber(userRequest.getNumber());

        if(userRepository.findByUsername(userRequest.getUsername()).isPresent()){
            throw new FieldAlreadyExists(format(
                    USER_EXISTS_WITH_USERNAME.getMessage(),
                    userRequest.getUsername()
            ));
        }

        if(userRepository.findByEmail(userRequest.getEmail()).isPresent()){
            throw new FieldAlreadyExists(format(
                    USER_EXISTS_WITH_EMAIL.getMessage(),
                    userRequest.getEmail()
            ));
        }


        userEntity.setUsername(userRequest.getUsername());
        userEntity.setName(userRequest.getName());
        userEntity.setSurname(userRequest.getSurname());
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setUserProfile(userProfile);
        userRepository.save(userEntity);
        return USER_DETAILS_UPDATED.getMessage();
    }

    @Override
    public String addSkillToUser(List<String> skillNames, String username) {
        UserEntity userEntity = getUserEntityByUsername(username);

        Optional<List<SkillEntity>> skillEntities = skillRepository.findAllByNameIn(skillNames);
        Set<SkillEntity> skills = new HashSet<>();

        skillEntities.ifPresent(
                skills::addAll
        );
        UserProfile userProfile = userEntity.getUserProfile();
        userProfile.setSkills(skills);
        skills.forEach(skillEntity -> skillEntity.getUserProfile().add(userProfile));
        userProfileRepository.save(userProfile);
        skillRepository.saveAll(skills);

        return SKILLS_ADDED_TO_USER.getMessage();
    }

    @Override
    public String deleteConnection(Long connectedUserId, String username) {
        UserEntity connectedUser = getUserEntityById(connectedUserId);

        UserEntity user = getUserEntityByUsername(username);

        if(user.getConnections().contains(connectedUser)) {
            user.getConnections().remove(connectedUser);
            connectedUser.setConnectionCount(connectedUser.getConnectionCount() - 1);
            user.setConnectionCount(user.getConnectionCount()-1);
            connectedUser.getConnections().remove(user);

            userRepository.save(user);
            userRepository.save(connectedUser);

            return CONNECTION_DELETED.getMessage();
        }

        throw new UnauthorizedException(NO_PERMISSION.getMessage());

    }

    @Override
    public DetailedUserResponse getDetailedUserInfo(Long userId) {
        UserEntity userEntity = getUserEntityById(userId);

        Long currentCompanyId = userEntity.getUserProfile().getCurrentCompanyId();

        CompanyResponse companyResponse =null;

        if(currentCompanyId != null) {
            companyResponse = jobClient.getCompanyById(currentCompanyId).getBody();
        }

        return USER_ENTITY_MAPPER.toDetailedUserResponse(userEntity,companyResponse);

    }

    @Override
    public CompanyUserResponse findUserCompanyByUsername(String username) {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                USER_NOT_FOUND_WITH_NAME.getMessage(),
                                username
                        )
                ));

        return CompanyUserResponse.builder()
                .userId(userEntity.getId())
                .companyId(userEntity.getUserProfile().getCurrentCompanyId())
                .build();

    }

    @Override
    public UserResponse findUserByUsernameOrEmail(String email, String username) {
        return userRepository.findByEmailOrUsername(email,username)
                .map(USER_ENTITY_MAPPER::toUserResponse)
                .orElse(null);
    }

    @Override
    public UserResponse findUserById(Long id) {
        return userRepository.findById(id)
                .map(USER_ENTITY_MAPPER::toUserResponse)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                USER_NOT_FOUND_WITH_ID.getMessage(),
                                id
                        )
                ));
    }

    @Override
    public List<UserSearchResponse> searchUsers(String keyword) {
        return userRepository.searchUserEntities(keyword).stream()
                .map(USER_ENTITY_MAPPER::mapToUserSearchResponse)
                .toList();
    }

    private UserEntity getUserEntityById(Long userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException(
                        format(
                                USER_NOT_FOUND_WITH_ID.getMessage(),
                                userId
                        ))
        );
    }

    private UserEntity getUserEntityByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(()->new NotFoundException(
                        format(
                                USER_NOT_FOUND_WITH_NAME.getMessage(),
                                username
                        )
                ));
    }
}
