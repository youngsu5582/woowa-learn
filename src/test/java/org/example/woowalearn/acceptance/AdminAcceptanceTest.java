package org.example.woowalearn.acceptance;

import org.example.woowalearn.acceptance.config.AcceptanceTest;
import org.example.woowalearn.user.domain.FormStatus;
import org.example.woowalearn.user.dto.ApplyChangeRequest;
import org.junit.jupiter.api.Test;

import static org.example.woowalearn.acceptance.step.given.AuthGivenStep.어드민_토큰_반환;
import static org.example.woowalearn.acceptance.step.given.AuthGivenStep.회원가입후_토큰_반환;
import static org.example.woowalearn.acceptance.step.given.UserGivenStep.교육자_지원서_정보_성성;
import static org.example.woowalearn.acceptance.step.given.UserGivenStep.본인_지원서_결과_조회;
import static org.example.woowalearn.acceptance.step.then.CommonThenStep.조회_성공한지_검증;
import static org.example.woowalearn.acceptance.step.then.UserThenStep.지원_결과_특정_상태인지_검증;
import static org.example.woowalearn.acceptance.step.when.AdminWhenStep.지원서_거절;
import static org.example.woowalearn.acceptance.step.when.AdminWhenStep.지원서_승인;
import static org.example.woowalearn.acceptance.step.when.UserWhenStep.교육자_신청;
import static org.example.woowalearn.acceptance.step.when.UserWhenStep.본인_지원서_조회;

@AcceptanceTest
class AdminAcceptanceTest {
    private final String 신청_이유 = "자바 TDD 를 가르키고 싶어요";
    private final String 계약자명 = "조이썬";
    private final String 전화번호 = "010-3320-1234";
    private final String 참고_링크 = "https://github.com/youngsu5582/woowa-learn";
    private final String 연락_이메일 = "joyson5582@gmail.com";

    @Test
    void 어드민이_지원서를_승인한다() {
        final var 액세스_토큰 = 회원가입후_토큰_반환("joyson5582@gmail.com", "password1234")
                .accessToken();
        final var 교육자_지원서_정보 = 교육자_지원서_정보_성성(
                신청_이유, 전화번호, 참고_링크, 계약자명, 연락_이메일
        );
        교육자_신청(액세스_토큰, 교육자_지원서_정보);
        final var 어드민_토큰 = 어드민_토큰_반환();

        final var 결과 = 지원서_승인(어드민_토큰, new ApplyChangeRequest(
                본인_지원서_결과_조회(액세스_토큰).applyFormId(),
                "TDD 의 신"
        ));
        조회_성공한지_검증(결과);
        final var 지원서_조회_결과 = 본인_지원서_조회(액세스_토큰);
        지원_결과_특정_상태인지_검증(지원서_조회_결과, FormStatus.APPROVE);
    }
    @Test
    void 어드민이_지원서를_거절한다() {
        final var 액세스_토큰 = 회원가입후_토큰_반환("joyson5582@gmail.com", "password1234")
                .accessToken();
        final var 교육자_지원서_정보 = 교육자_지원서_정보_성성(
                신청_이유, 전화번호, 참고_링크, 계약자명, 연락_이메일
        );
        교육자_신청(액세스_토큰, 교육자_지원서_정보);
        final var 어드민_토큰 = 어드민_토큰_반환();

        final var 결과 = 지원서_거절(어드민_토큰, new ApplyChangeRequest(
                본인_지원서_결과_조회(액세스_토큰).applyFormId(),
                "지원 자격 미달"
        ));
        조회_성공한지_검증(결과);
        final var 지원서_조회_결과 = 본인_지원서_조회(액세스_토큰);
        지원_결과_특정_상태인지_검증(지원서_조회_결과, FormStatus.APPROVE);
    }
}
