package lk.innoviv.mas.persistence.model;

import lk.innoviv.mas.security.model.Security.Authority;

import java.util.ArrayList;
import java.util.List;

public class UserGroup {

    public enum Type {
        Admin,
        User,
        BEUser,
    }

    private final Long id;

    private final Type name;

    private final List<Authority> authorities;

    private UserGroup(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.authorities = builder.authorities;
    }

    public Long getId() {
        return id;
    }

    public Type getType() {
        return name;
    }

    List<Authority> getAuthorities() {
        return authorities;
    }

    public boolean hasAuthority(Authority role) {
        return authorities.contains(role);
    }

    @Override
    public String toString() {
        return name.toString();
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Long id;
        private Type name;
        private List<Authority> authorities;

        private Builder() {
            authorities = new ArrayList<>();
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withName(Type name) {
            this.name = name;
            return this;
        }

        public Builder withAuthorities(List<Authority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public UserGroup build() {
            return new UserGroup(this);
        }
    }
}
