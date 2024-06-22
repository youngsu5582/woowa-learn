package org.example.woowalearn.user.dto;

public record UserCreateRequest(String email, String password, boolean isEmailAccept) {
}
