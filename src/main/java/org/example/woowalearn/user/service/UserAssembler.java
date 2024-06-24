package org.example.woowalearn.user.service;

import org.example.woowalearn.user.domain.User;
import org.example.woowalearn.user.dto.UserCreateRequest;
import org.example.woowalearn.user.dto.UserResponse;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAssembler {
    public UserResponse toResponse(final User user) {
        return new UserResponse(
                user.getId(),
                user.getEmailAsString(),
                user.isEmailAccept(),
                user.isMember()
        );
    }

    public User toEntity(final UserCreateRequest request, final PasswordEncoder passwordEncoder) {
        return new User(
                request.email(),
                passwordEncoder.encode(request.password()),
                request.isEmailAccept());
    }
}
