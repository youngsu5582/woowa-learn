package org.example.woowalearn.user.service;

import org.example.woowalearn.ServiceTest;
import org.example.woowalearn.exception.WoowaLearnException;
import org.example.woowalearn.user.domain.FormStatus;
import org.example.woowalearn.user.dto.ApplyTeacherRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ServiceTest
class ApplyServiceTest {
    @Autowired
    private ApplyService sut;

    @Test
    void 사용자_아이디와_지원정보를_통해_지원서를_저장한다(){
        final long userId = 5;
        final var request = new ApplyTeacherRequest(
                "자바 TDD 를 가르키고 싶어요"
                ,"010-3320-1234",
                "https://github.com/youngsu5582/woowa-learn",
                "조이썬",
                "joyson5582@gmail.com"
        );

        sut.applyForm(userId,request);

        final var result = sut.findMyApplyForm(userId);
        assertThat(result.applyStatus()).isEqualTo(FormStatus.PENDING.name());
        assertThat(result.contractName()).isEqualTo("조이썬");
    }
    @Test
    void 사용자_아이디로_이미_지원서를_작성했으면_예외를_발생한다(){
        final long userId = 5;
        final var request = new ApplyTeacherRequest(
                "자바 TDD 를 가르키고 싶어요"
                ,"010-3320-1234",
                "https://github.com/youngsu5582/woowa-learn",
                "조이썬",
                "joyson5582@gmail.com"
        );
        sut.applyForm(userId,request);

        assertThatThrownBy(()->sut.applyForm(userId,request))
                .isInstanceOf(WoowaLearnException.class);
    }
}
