package org.example.woowalearn.user.service;

import lombok.AllArgsConstructor;
import org.example.woowalearn.exception.WoowaLearnException;
import org.example.woowalearn.user.dto.UserCreateRequest;
import org.example.woowalearn.user.dto.UserResponse;
import org.example.woowalearn.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserAssembler userAssembler;

    @Transactional(readOnly = true)
    public UserResponse signup(final UserCreateRequest request) {
        if (userRepository.existsByEmailAddress(request.email())) {
            throw new WoowaLearnException(HttpStatus.NOT_FOUND, String.format("%s에 해당하는 이메일이 이미 있습니다.", request.email()));
        }
        final var user = userAssembler.toEntity(request);
        return userAssembler.toResponse(userRepository.save(user));
    }
}
