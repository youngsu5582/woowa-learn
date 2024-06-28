package org.example.woowalearn.user.service;

import lombok.extern.slf4j.Slf4j;
import org.example.woowalearn.user.domain.FormStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import static org.example.woowalearn.user.constant.EmailConstant.*;

@Slf4j
@Component
public class EmailSender {

    private final String from;
    private final JavaMailSender javaMailSender;

    public EmailSender(final JavaMailSender javaMailSender,
                       @Value("spring.mail.username") final String from) {
        this.javaMailSender = javaMailSender;
        this.from = from;
    }

    public void sendApplyJudgeMail(final String contractName, final String contactEmail, final String applyStatus) {
        final FormStatus formStatus = FormStatus.from(applyStatus.toUpperCase());
        switch (formStatus) {
            case DENY -> send(createApplyDenyMessage(contractName, contactEmail));
            case APPROVE -> send(createApplyApproveMessage(contractName, contactEmail));
            default -> {}
        }
    }

    private void send(final SimpleMailMessage message) {
        try {
            javaMailSender.send(message);
        } catch (final MailSendException ignored) {
            log.warn("이메일 전송 실패 from = {} to = {}cause = {}", message.getFrom(), message.getTo(), ignored.getMessage());
        }
    }

    private SimpleMailMessage createApplyApproveMessage(final String contractName, final String contactEmail) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(contactEmail);
        message.setText(String.format(APPROVE_EMAIL_TEMPLATE, contractName));
        message.setFrom(from);
        message.setSubject(TITLE);
        return message;
    }

    private SimpleMailMessage createApplyDenyMessage(final String contractName, final String contactEmail) {
        final SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(contactEmail);
        message.setText(String.format(DENY_EMAIL_TEMPLATE, contractName));
        message.setFrom(from);
        message.setSubject(TITLE);
        return message;
    }
}
