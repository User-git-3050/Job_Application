package com.example.msjob.client;

import com.example.msjob.dao.response.CompanyUserResponse;
import com.example.msjob.dao.response.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "MS-USER"
)
public interface UserClient {
    @GetMapping("api/v1/user/username/{username}")
    ResponseEntity<UserResponse> findUserByUsername(@PathVariable String username);

    @GetMapping("api/v1/user/userId/companyId/{username}")
    ResponseEntity<CompanyUserResponse> findCompanyUserByUsername(@PathVariable String username);
}
