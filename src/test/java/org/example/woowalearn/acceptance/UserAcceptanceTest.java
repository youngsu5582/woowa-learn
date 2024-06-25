package org.example.woowalearn.acceptance;

import org.example.woowalearn.acceptance.config.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.example.woowalearn.acceptance.step.given.AuthGivenStep.회원가입후_토큰_반환;
import static org.example.woowalearn.acceptance.step.given.UserGivenStep.교육자_지원서_정보_성성;
import static org.example.woowalearn.acceptance.step.then.CommonThenStep.생성_성공한지_검증;
import static org.example.woowalearn.acceptance.step.then.CommonThenStep.잘못된_요청인지_검증;
import static org.example.woowalearn.acceptance.step.when.UserWhenStep.교육자_신청;

@AcceptanceTest
public class UserAcceptanceTest {
    private final String 신청_이유 = "자바 TDD 를 가르키고 싶어요";
    private final String 계약자명 = "조이썬";
    private final String 전화번호 = "010-3320-1234";
    private final String 참고_링크 = "https://github.com/youngsu5582/woowa-learn";
    private final String 연락_이메일 = "joyson5582@gmail.com";


    @Nested
    @DisplayName("티쳐 신청 케이스")
    class applyTeacherCase {
        @Test
        void 교육자_신청을_성공한다() {
            final var 액세스_토큰 = 회원가입후_토큰_반환("joyson5582@gmail.com", "password1234")
                    .accessToken();
            final var 교육자_지원서_정보 = 교육자_지원서_정보_성성(
                    신청_이유,
                    전화번호,
                    참고_링크,
                    계약자명,
                    연락_이메일
            );

            final var 결과 = 교육자_신청(액세스_토큰, 교육자_지원서_정보);

            생성_성공한지_검증(결과);
        }

        @Test
        void 신청은_하나만_가능하다() {
            final var 액세스_토큰 = 회원가입후_토큰_반환("joyson5582@gmail.com", "password1234")
                    .accessToken();
            final var 교육자_지원서_정보 = 교육자_지원서_정보_성성(
                    신청_이유, 전화번호, 참고_링크, 계약자명, 연락_이메일
            );
            final var 교육자_신청_결과 = 교육자_신청(액세스_토큰, 교육자_지원서_정보);
            생성_성공한지_검증(교육자_신청_결과);

            final var 중복_교육자_신청_결과 = 교육자_신청(액세스_토큰, 교육자_지원서_정보);

            잘못된_요청인지_검증(중복_교육자_신청_결과);

        }
    }
}
