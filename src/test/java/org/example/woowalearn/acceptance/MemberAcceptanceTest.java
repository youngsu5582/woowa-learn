package org.example.woowalearn.acceptance;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class MemberAcceptanceTest {
    @Nested
    @DisplayName("티쳐 신청 케이스")
    class applyTeacherCase {
        @Test
        void 교육자_신청을_성공한다() {
            // 로그인을 한다
            // 지원서 정보 작성

            // 교육자 신청을 한다

            // 성공한다
        }

        @Test
        void 신청은_하나만_가능하다() {
            // 로그인을 한다
            // 지원서 정보 작성
            // 교육자 신청을 한다
            
            // 교육자 신청을 한번 더 한다

            // 실패한다
        }
    }
}
