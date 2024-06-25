package org.example.woowalearn.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

    public ApplyForm(final Long userId, final String contractName, final String phoneNumber, final String explainLink, final String contactEmail, final String applyReason, final FormStatus formStatus) {
        this.userId = userId;
        this.contractName = contractName;
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.contactEmail = new Email(contactEmail);
        this.explainLink = explainLink;
        this.formStatus = formStatus;
        this.applyReason = applyReason;
    }
}
