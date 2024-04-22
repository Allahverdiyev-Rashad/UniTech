package az.uni.unitech.controller;

import az.uni.unitech.dto.request.UserLoginRequest;
import az.uni.unitech.dto.request.UserRegistration;
import az.uni.unitech.dto.response.UserResponse;
import az.uni.unitech.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    private ResponseEntity<UserResponse> register(
            @Valid @RequestBody UserRegistration registration) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.registerUser(registration));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserLoginRequest userLogin) {
        return ResponseEntity.ok(userService.loginUser(userLogin));
    }

}