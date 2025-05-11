package com.product.userservice.service.inter;

import com.product.userservice.dto.request.UserRequest;
import com.product.userservice.dto.request.UserSearchCriteria;
import com.product.userservice.dto.response.CompanyUserResponse;
import com.product.userservice.dto.response.DetailedUserResponse;
import com.product.userservice.dto.response.UserResponse;
import com.product.userservice.dto.response.UserSearchResponse;
import com.product.userservice.enums.RoleEnum;

import java.util.List;

public interface UserService {
    UserResponse findByUsername(String username);

    UserResponse findUserByEmail(String email);

    UserResponse saveUser(UserRequest userRequest);

    List<UserResponse> getAllUsers();

    String setUserRole(Long userId, RoleEnum roleEnum);

    Long findUserIdByUsername(String username);

    Boolean isUserExist(String username);

    void deleteUser(Long userId);

    String editUserDetails(UserRequest userRequest, String username);

    String addSkillToUser(List<String> skillNames, String username);

    String deleteConnection(Long connectedUserId, String username);

    DetailedUserResponse getDetailedUserInfo(Long userId);

    CompanyUserResponse findUserCompanyByUsername(String username);

    UserResponse findUserByUsernameOrEmail(String email, String username);

    UserResponse findUserById(Long id);

    List<UserSearchResponse> searchUsers(String keyword);

}
