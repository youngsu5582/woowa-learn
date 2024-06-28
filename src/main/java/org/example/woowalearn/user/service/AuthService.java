package org.example.woowalearn.user.service;

import lombok.AllArgsConstructor;
import org.example.woowalearn.exception.WoowaLearnException;
import org.example.woowalearn.global.config.security.JwtProvider;
import org.example.woowalearn.user.domain.User;
import org.example.woowalearn.user.dto.TokenResponse;
import org.example.woowalearn.user.dto.UserCreateRequest;
import org.example.woowalearn.user.dto.UserLoginRequest;
import org.example.woowalearn.user.dto.UserResponse;
import org.example.woowalearn.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final UserAssembler userAssembler;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional(readOnly = true)
    public UserResponse signup(final UserCreateRequest request) {
        if (userRepository.existsByEmailAddress(request.email())) {
            throw new WoowaLearnException(HttpStatus.BAD_REQUEST, String.format("%s에 해당하는 이메일이 이미 있습니다.", request.email()));
        }
        final var user = userAssembler.toEntity(request, passwordEncoder);

        return userAssembler.toResponse(userRepository.save(user));
    }

    @Transactional(readOnly = true)
    public TokenResponse login(final UserLoginRequest request) {
        final User user = userRepository.findByEmailAddress(request.email())
                .orElseThrow(() -> new WoowaLearnException(HttpStatus.BAD_REQUEST, String.format("%s는 존재하지 않는 이메일입니다.", request.email())));

        if (!user.isEqualPassword(passwordEncoder, request.password())) {
            throw new WoowaLearnException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }

        return jwtProvider.generateToken(user.getEmailAsString());
    }
}
