package org.example.woowalearn.user.dto;

public record UserResponse(
        long id,
        String email,
        boolean isEmailAccepted,
        boolean isMember
) {
}
