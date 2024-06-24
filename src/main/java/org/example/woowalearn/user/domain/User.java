package org.example.woowalearn.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Password password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private boolean isEmailAccept;

    public User(final Email email, final Password password, final boolean isEmailAccept) {
        this.email = email;
        this.password = password;
        this.isEmailAccept = isEmailAccept;
        this.role = Role.MEMBER;
    }
    public boolean isMember(){
        return this.role == Role.MEMBER;
    }

    public String getEmailAsString() {
        return email.address();
    }
}
