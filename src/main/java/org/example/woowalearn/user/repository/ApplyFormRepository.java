package org.example.woowalearn.user.repository;

import org.example.woowalearn.exception.WoowaLearnException;
import org.example.woowalearn.user.domain.ApplyForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public interface ApplyFormRepository extends JpaRepository<ApplyForm, Long> {
    boolean existsByUserId(long userId);

    Optional<ApplyForm> findByUserId(long userId);

    default ApplyForm getByIdOrThrow(final long id) {
        return findById(id)
                .orElseThrow(() -> new WoowaLearnException(HttpStatus.NOT_FOUND, String.format("%d에 해당하는 지원서가 없습니다.", id)));
    }
}
