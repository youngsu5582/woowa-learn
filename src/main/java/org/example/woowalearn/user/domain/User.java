package org.example.woowalearn.user.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    public User(final String email,final String password,final boolean isEmailAccept) {
        this.email = new Email(email);
        this.password = new Password(password);
        this.isEmailAccept = isEmailAccept;
        this.role = Role.MEMBER;
    }

    public boolean isMember(){
        return this.role == Role.MEMBER;
    }

    public boolean matchPassword(final PasswordEncoder encoder, final String password){
        return this.password.matchPassword(encoder,password);
    }

    public String getEmailAsString() {
        return email.address();
    }
}
