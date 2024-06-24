package org.example.woowalearn.user.service;

import org.example.woowalearn.user.domain.Email;
import org.example.woowalearn.user.domain.Password;
import org.example.woowalearn.user.domain.User;
import org.example.woowalearn.user.dto.UserCreateRequest;
import org.example.woowalearn.user.dto.UserResponse;
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

    public User toEntity(final UserCreateRequest request) {
        return new User(new Email(request.email()), new Password(request.password()), request.isEmailAccept());
    }
}
