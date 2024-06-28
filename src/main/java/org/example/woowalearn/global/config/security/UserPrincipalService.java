package org.example.woowalearn.global.config.security;

import lombok.AllArgsConstructor;
import org.example.woowalearn.user.domain.UserPrincipal;
import org.example.woowalearn.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class UserPrincipalService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserPrincipal loadUserByUsername(final String username) throws UsernameNotFoundException {
        return userRepository.findByEmailAddress(username)
                .map(UserPrincipal::from)
                .orElseThrow(()->new UsernameNotFoundException(username));
    }
}
