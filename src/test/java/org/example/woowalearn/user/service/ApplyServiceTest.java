package org.example.woowalearn.user.service;

import org.example.woowalearn.ServiceTest;
import org.example.woowalearn.exception.WoowaLearnException;
import org.example.woowalearn.user.domain.ApplyForm;
import org.example.woowalearn.user.domain.FormStatus;
import org.example.woowalearn.user.domain.User;
import org.example.woowalearn.user.dto.ApplyChangeRequest;
import org.example.woowalearn.user.dto.ApplyTeacherRequest;
import org.example.woowalearn.user.repository.ApplyFormRepository;
import org.example.woowalearn.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.woowalearn.TestAdminInitializer.ADMIN;

@ServiceTest
class ApplyServiceTest {
    @Autowired
    private ApplyService sut;
    @Autowired
    private ApplyFormRepository applyFormRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void 사용자_아이디와_지원정보를_통해_지원서를_저장한다() {
        final long userId = 5;
        final var request = getRequest();

        sut.applyForm(userId, request);

        final var result = sut.findApplyFormWithUserId(userId);
        assertThat(result.applyStatus()).isEqualTo(FormStatus.PENDING.name());
        assertThat(result.contractName()).isEqualTo("조이썬");
    }

    @Test
    void 사용자_아이디로_이미_지원서를_작성했으면_예외를_발생한다() {
        final long userId = 5;
        final var request = getRequest();
        sut.applyForm(userId, request);

        assertThatThrownBy(() -> sut.applyForm(userId, request))
                .isInstanceOf(WoowaLearnException.class);
    }

    @Test
    void 운영자가_지원서를_승인한다() {
        final long userId = 5;
        final ApplyForm applyForm = applyFormRepository.save(getDomain(userId, getRequest()));

        sut.approveApply(ADMIN.getId(), new ApplyChangeRequest(
                applyForm.getId(),
                "승인 완료"
        ));

        final var result = sut.findApplyFormWithUserId(userId);
        assertThat(result.applyStatus()).isEqualTo(FormStatus.APPROVE.name());
    }

    @Test
    void 일반_사용자가_지원서를_변경하면_예외를_발생한다() {
        final long userId = userRepository.save(new User("joyson5582@gmail.com", "password1234", true))
                .getId();
        final ApplyForm applyForm = applyFormRepository.save(getDomain(userId, getRequest()));

        assertThatThrownBy(() -> sut.approveApply(userId, new ApplyChangeRequest(
                applyForm.getId(),
                "승인 완료"
        ))).isInstanceOf(WoowaLearnException.class);
    }

    @Test
    void 거절된_지원서를_승인하면_예외를_발생한다() {
        final long userId = userRepository.save(new User("joyson5582@gmail.com", "password1234", true))
                .getId();
        final ApplyForm applyForm = applyFormRepository.save(getDomain(userId, getRequest()));
        applyForm.deny("기타사유", ADMIN.getId());

        assertThatThrownBy(() -> sut.approveApply(userId, new ApplyChangeRequest(
                applyForm.getId(),
                "승인 완료"
        ))).isInstanceOf(WoowaLearnException.class);
    }
    @Test
    void 승인된_지원서를_거절하면_예외를_발생한다() {
        final long userId = userRepository.save(new User("joyson5582@gmail.com", "password1234", true))
                .getId();
        final ApplyForm applyForm = applyFormRepository.save(getDomain(userId, getRequest()));
        applyForm.approve("완벽강의", ADMIN.getId());

        assertThatThrownBy(() -> sut.denyApply(userId, new ApplyChangeRequest(
                applyForm.getId(),
                "자격 부족으로 인한 거절"
        ))).isInstanceOf(WoowaLearnException.class);
    }

    private ApplyTeacherRequest getRequest() {
        return new ApplyTeacherRequest(
                "자바 TDD 를 가르키고 싶어요"
                , "010-3320-1234",
                "https://github.com/youngsu5582/woowa-learn",
                "조이썬",
                "joyson5582@gmail.com"
        );
    }

    private ApplyForm getDomain(final long userId, final ApplyTeacherRequest request) {
        return new ApplyForm(
                userId,
                request.contractName(),
                request.phoneNumber(),
                request.explainLink(),
                request.contactEmail(),
                request.applyReason(),
                FormStatus.PENDING
        );
    }
}
