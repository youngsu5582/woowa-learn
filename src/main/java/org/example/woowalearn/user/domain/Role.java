package org.example.woowalearn.user.domain;

public enum Role {
    ADMIN("ADMIN"),
    MEMBER("MEMBER"),
    TEACHER("TEACHER");
    private String name;

    Role(final String name) {
        this.name = name;
    }
    public static Role from(final String role) {
        for (final Role roles : Role.values()) {
            if (roles.name.equals(role)) {
                return roles;
            }
        }
        throw new IllegalArgumentException(role);
    }
}
