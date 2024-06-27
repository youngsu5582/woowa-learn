package org.example.woowalearn.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.woowalearn.exception.WoowaLearnException;
import org.springframework.http.HttpStatus;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApplyForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long userId;

    private String contractName;

    @Embedded
    private PhoneNumber phoneNumber;

    private String explainLink;

    @Embedded
    private Email contactEmail;

    private String applyReason;

    @Enumerated(EnumType.STRING)
    private FormStatus formStatus;

    private String description;
    private Long approveAdminId;

    public ApplyForm(final long userId, final String contractName, final String phoneNumber, final String explainLink,
                     final String contactEmail, final String applyReason, final FormStatus formStatus) {
        this.userId = userId;
        this.contractName = contractName;
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.contactEmail = new Email(contactEmail);
        this.explainLink = explainLink;
        this.formStatus = formStatus;
        this.applyReason = applyReason;
        this.description = "";
        this.approveAdminId = null;
    }

    public void approve(final String description,final long adminId) {
        if(this.formStatus== FormStatus.DENY){
            throw new WoowaLearnException(HttpStatus.BAD_REQUEST,"이미 거절된 지원서입니다.");
        }
        this.formStatus = FormStatus.APPROVE;
        this.description = description;
        this.approveAdminId = adminId;
    }
    public void deny(final String description,final long adminId) {
        if(this.formStatus== FormStatus.APPROVE){
            throw new WoowaLearnException(HttpStatus.BAD_REQUEST,"이미 승인된 지원서입니다.");
        }
        this.formStatus = FormStatus.DENY;
        this.description = description;
        this.approveAdminId = adminId;
    }
}
