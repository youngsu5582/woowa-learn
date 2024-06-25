package org.example.woowalearn.user.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
    private final String email;
    private final String password;
    private final long userId;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(final String email, final String password, final long userId, final Collection<? extends GrantedAuthority> authorities) {
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.authorities = authorities;
    }
    public static UserPrincipal from(final User user) {
        return new UserPrincipal(
                user.getEmailAsString(),
                user.getPassword().password(),
                user.getId(),
                List.of(new SimpleGrantedAuthority(user.getRole().name()))
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }
    public long getUserId() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "UserPrincipal{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
