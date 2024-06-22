package org.example.woowalearn.user.repository;

import org.example.woowalearn.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAddress(String address);
}
