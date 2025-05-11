package com.product.userservice.controller;

import com.product.userservice.dto.request.UserRequest;
import com.product.userservice.dto.request.UserSearchCriteria;
import com.product.userservice.dto.response.CompanyUserResponse;
import com.product.userservice.dto.response.DetailedUserResponse;
import com.product.userservice.dto.response.UserResponse;
import com.product.userservice.dto.response.UserSearchResponse;
import com.product.userservice.enums.RoleEnum;
import com.product.userservice.service.inter.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.findUserById(id));
    }
    @GetMapping("username/{username}")
    public ResponseEntity<UserResponse> findUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping("{email}/{username}")
    public ResponseEntity<UserResponse> getUserByEmailOrUsername(@PathVariable String email, @PathVariable String username){
        return ResponseEntity.ok(userService.findUserByUsernameOrEmail(email, username));
    }

    @GetMapping("email/{email}")
    public ResponseEntity<UserResponse> findUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.findUserByEmail(email));
    }

    @GetMapping("userId/companyId/{username}")
    public ResponseEntity<CompanyUserResponse> findCompanyUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findUserCompanyByUsername(username));
    }

    @GetMapping("search")
    public ResponseEntity<List<UserSearchResponse>> searchUsers(@RequestParam String keyword){
        return ResponseEntity.ok(userService.searchUsers(keyword));
    }

    @PostMapping()
    public ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserRequest userRequest){
        return ResponseEntity.ok(userService.saveUser(userRequest));
    }

    @PostMapping("{userId}/role")
    public ResponseEntity<String> setUserRole(@PathVariable Long userId,@RequestBody RoleEnum roleEnum){
        return ResponseEntity.ok(userService.setUserRole(userId, roleEnum));
    }

    @GetMapping("find/userId/{username}")
    public ResponseEntity<Long> findUserIdByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findUserIdByUsername(username));
    }

    @GetMapping("existed_user/{username}")
    public ResponseEntity<Boolean> existedUser(@PathVariable String username){
        return ResponseEntity.ok(userService.isUserExist(username));
    }

    @GetMapping("detailed/{userId}")
    public ResponseEntity<DetailedUserResponse> getDetailedUserInfo(@PathVariable Long userId){
        return ResponseEntity.ok(userService.getDetailedUserInfo(userId));
    }

    @PutMapping("edit")
    public ResponseEntity<String> editUserDetails (@Valid @RequestBody UserRequest userRequest, @RequestHeader String username){
        return ResponseEntity.ok(userService.editUserDetails(userRequest,username));
    }

    @PutMapping("skill")
    public ResponseEntity<String> addSkill(@RequestParam List<String> skillNames, @RequestHeader String username){
        return ResponseEntity.ok(userService.addSkillToUser(skillNames,username));
    }

    @DeleteMapping("connection/{connectedUserId}")
    public ResponseEntity<String> deleteConnection(@RequestHeader String username,@PathVariable Long connectedUserId){
        return ResponseEntity.ok(userService.deleteConnection(connectedUserId, username));
    }







}
