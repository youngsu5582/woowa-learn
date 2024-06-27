package org.example.woowalearn.user.repository;

import org.example.woowalearn.user.domain.ApplyForm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplyFormRepository  extends JpaRepository<ApplyForm, Long> {
    boolean existsByUserId(long userId);
    Optional<ApplyForm> findByUserId(long userId);
}
