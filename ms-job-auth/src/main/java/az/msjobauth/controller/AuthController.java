package az.msjobauth.controller;

import az.msjobauth.dao.*;
import az.msjobauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth/")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody AuthRequest authRequest) {
        return ResponseEntity.ok(authService.generateToken(authRequest));
    }

    @PostMapping("/register/user")
    public ResponseEntity<String> register(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(authService.registerUser(userRequest));
    }

    @PostMapping("/register/owner")
    public ResponseEntity<String> registerOwner(@RequestBody OwnerRegistrationRequest ownerRegistrationRequest) {
        return ResponseEntity.ok(authService.registerOwner(ownerRegistrationRequest));
    }

    @PostMapping("register/recruiter")
    public ResponseEntity<String> registerRecruiter(@RequestBody RecruiterRegistrationRequest recruiterRegistrationRequest){
        return ResponseEntity.ok(authService.registerRecruiter(recruiterRegistrationRequest));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<String> registerAdmin(@RequestBody AdminRegistrationRequest adminRegistrationRequest) {
        return ResponseEntity.ok(authService.registerAdmin(adminRegistrationRequest));
    }

}
