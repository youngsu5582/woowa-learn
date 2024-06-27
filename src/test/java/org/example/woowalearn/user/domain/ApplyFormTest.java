package org.example.woowalearn.user.domain;

import org.example.woowalearn.exception.WoowaLearnException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplyFormTest {
    @Test
    void 이미_거절된_상태면_승인할_수_없다() {
        final ApplyForm form = getDomain(FormStatus.DENY);
        assertThatThrownBy(()->form.approve("거절이여도 승인은 하고 싶어",3))
                .isInstanceOf(WoowaLearnException.class);
    }
    @Test
    void 이미_승인된_상태면_거절할_수_없다() {
        final ApplyForm form = getDomain(FormStatus.APPROVE);
        assertThatThrownBy(()->form.deny("승인이여도 거절은 하고 싶어",3))
                .isInstanceOf(WoowaLearnException.class);
    }

    private ApplyForm getDomain(final FormStatus formStatus) {
        return new ApplyForm(
                5,
                "조이썬",
                "010-3320-1234",
                "https://github.com/youngsu5582/woowa-learn",
                "joyson5582@gmail.com",
                "자바 TDD 를 가르키고 싶어요",
                formStatus
        );
    }
}
