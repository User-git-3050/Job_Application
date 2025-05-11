package az.msjobauth.client;

import az.msjobauth.dao.UserRequest;
import az.msjobauth.dao.UserResponse;
import az.msjobauth.enums.RoleEnum;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "MS-USER")
public interface UserClient {
    @GetMapping("api/v1/user/username/{username}")
    ResponseEntity<UserResponse> findUserByUsername(@PathVariable String username);

    @GetMapping("api/v1/user/{email}/{username}")
    ResponseEntity<UserResponse> getUserByEmailOrUsername(@PathVariable String email, @PathVariable String username);

    @PostMapping("api/v1/user/{userId}/role")
    ResponseEntity<String> setUserRole(@PathVariable Long userId,@RequestBody RoleEnum roleEnum);

    @PostMapping("api/v1/user/")
    ResponseEntity<UserResponse> saveUser(@Valid @RequestBody UserRequest userRequest);
}
