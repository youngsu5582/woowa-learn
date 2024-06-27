package org.example.woowalearn.user.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.example.woowalearn.user.domain.UserPrincipal;
import org.example.woowalearn.user.dto.*;
import org.example.woowalearn.user.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/auth/signup")
    public ResponseEntity<UserResponse> signUp(@RequestBody final UserCreateRequest request) {
        final var result = authService.signup(request);
        return ResponseEntity.created(URI.create("/user/"+result.id()))
                .body(result);
    }
    @PostMapping("/auth/login")
    public ResponseEntity<TokenResponse> login(@RequestBody final UserLoginRequest request, final HttpServletResponse response) {
        final var result = authService.login(request);

        final Cookie cookie = new Cookie("token", result.accessToken());
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(result);
    }
    @GetMapping("/auth/check")
    public ResponseEntity<CheckResponse> check(@AuthenticationPrincipal final UserPrincipal userPrincipal){
        return ResponseEntity.ok(
                new CheckResponse(userPrincipal.getUsername())
        );
    }
}
