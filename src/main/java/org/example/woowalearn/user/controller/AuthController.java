package org.example.woowalearn.user.controller;

import lombok.AllArgsConstructor;
import org.example.woowalearn.user.dto.TokenResponse;
import org.example.woowalearn.user.dto.UserCreateRequest;
import org.example.woowalearn.user.dto.UserLoginRequest;
import org.example.woowalearn.user.dto.UserResponse;
import org.example.woowalearn.user.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
public class AuthController {
    AuthService authService;
    @PostMapping("/auth/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody final UserCreateRequest request) {
        final var result = authService.signup(request);
        return ResponseEntity.created(URI.create("/user/"+result.id()))
                .body(result);
    }
    @PostMapping("/auth/login")
    public ResponseEntity<TokenResponse> login(@RequestBody final UserLoginRequest request) {
        final var result = authService.login(request);
        return ResponseEntity.ok(result);
    }
}
