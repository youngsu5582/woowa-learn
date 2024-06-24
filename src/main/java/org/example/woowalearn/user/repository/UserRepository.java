package org.example.woowalearn.user.repository;

import org.example.woowalearn.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAddress(String address);
    Optional<User> findByEmailAddress(String email);
}
