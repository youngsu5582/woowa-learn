package org.example.woowalearn.acceptance;

import org.example.woowalearn.acceptance.config.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.example.woowalearn.acceptance.step.given.AuthGivenStep.회원가입_정보_생성;
import static org.example.woowalearn.acceptance.step.then.AuthThenStep.회원가입_성공한지_검증;
import static org.example.woowalearn.acceptance.step.then.CommonThenStep.생성_성공한지_검증;
import static org.example.woowalearn.acceptance.step.then.CommonThenStep.잘못된_요청인지_검증;
import static org.example.woowalearn.acceptance.step.when.AuthWhenStep.회원가입;

@AcceptanceTest
class AuthAcceptanceTest {
    private final String 이메일 = "joyson5582@gmail.com";
    private final String 비밀번호 = "password1234";
    private final boolean 이메일_수신_동의 = true;

    @Nested
    @DisplayName("회원가입")
    class RegisterCase{
        @Test
        void 회원가입을_성공한다() {
            final var 회원가입_사용자_정보 = 회원가입_정보_생성(
                    이메일,
                    비밀번호,
                    이메일_수신_동의
            );

            final var 회원가입_결과 = 회원가입(회원가입_사용자_정보);

            생성_성공한지_검증(회원가입_결과);
            회원가입_성공한지_검증(회원가입_결과);
        }

        @Test
        void 중복_이메일이_있을시_실패한다() {
            final var 회원가입_사용자_정보 = 회원가입_정보_생성(
                    이메일,
                    비밀번호,
                    이메일_수신_동의
            );

            final var 회원가입_결과 = 회원가입(회원가입_사용자_정보);
            회원가입_성공한지_검증(회원가입_결과);

            final var 중복_이메일_회원가입_결과 = 회원가입(회원가입_사용자_정보);
            잘못된_요청인지_검증(중복_이메일_회원가입_결과);
        }
    }
    @Nested
    @DisplayName("로그인")
    class LoginCase{
        @Test
        void 로그인을_성공한다(){
            // 회원가입

            // 로그인

            // 성공
        }
        @Test
        void 없는_이메일로_로그인_할_시_실패한다(){
            // 회원가입

            // 없는 이메일로 로그인

            // 실패
        }
        @Test
        void 비밀번호가_틀리면_실패한다(){
            // 회원가입

            // 다른 비밀번호로 로그인

            // 실패
        }
    }

}
