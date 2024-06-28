package org.example.woowalearn.user.constant;

public class EmailConstant {
    private EmailConstant(){}
    public static final String APPROVE_EMAIL_TEMPLATE =
            """
                        안녕하세요, %s님
                        우아런 강사 신청 결과에 대해 알려드립니다.
                                    
                        [신청 결과]
                        축하드립니다! 귀하의 강사 신청이 성공적으로 승인되었습니다.
                        앞으로 우아런에서 강사로서 활동하게 되실 것입니다.
                                    
                        [차후 과정]
                        1. 계약서 서명: 첨부된 계약서를 검토하시고 서명 후 보내주시기 바랍니다.
                        2. 강의 준비: 강의 자료와 교안을 준비해 주시고, 궁금한 경우 차후 보내주는 링크를 참고해주세요.
                                    
                        추가 문의 사항이 있으시면 언제든지 연락 주시기 바랍니다.
                                    
                        감사합니다.
                        우아런 드림
                    """;
    public static final String DENY_EMAIL_TEMPLATE =
            """
                        안녕하세요, %s님
                        우아런 강사 신청 결과에 대해 알려드립니다.
                                    
                        [신청 결과]
                        죄송합니다. 귀하의 강사 신청이 승인되지 않았습니다.
                        이번에는 아쉽게도 귀하의 신청이 기준을 충족하지 못했습니다.
                                    
                        [추후 안내]
                        1. 다음 기회: 향후 기회에 다시 지원해 주시면 감사하겠습니다.
                        2. 피드백 요청: 추가적인 피드백이 필요하시면 언제든지 연락 주시기 바랍니다.
                                    
                        추가 문의 사항이 있으시면 언제든지 연락 주시기 바랍니다.
                                    
                        감사합니다.
                        우아런 드림
                    """;
    public static final String TITLE = "우아런 강사 신청 결과";
}
