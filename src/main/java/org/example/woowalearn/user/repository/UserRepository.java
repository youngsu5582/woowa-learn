package org.example.woowalearn.user.repository;

import org.example.woowalearn.exception.WoowaLearnException;
import org.example.woowalearn.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailAddress(String address);

    Optional<User> findByEmailAddress(String email);

    default User getByIdOrThrow(final long id) {
        return findById(id)
                .orElseThrow(() -> new WoowaLearnException(HttpStatus.NOT_FOUND, String.format("%d에 해당하는 사용자가 없습니다.", id)));
    }
}
