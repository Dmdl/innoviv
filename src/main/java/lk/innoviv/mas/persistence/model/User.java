package lk.innoviv.mas.persistence.model;

import lk.innoviv.mas.security.model.Security.Authority;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

public class User {
    private final Long id;

    private final String username;

    private final String password;

    private final boolean isEnabled;

    private final boolean isLocked;

    private final UserGroup userGroup;

    private final Instant createdTimestamp;

    private final ZonedDateTime lastAccessTimestamp;

    private final Instant modifiedTimestamp;

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.userGroup = builder.userGroup;
        this.createdTimestamp = builder.createdTimestamp;
        this.modifiedTimestamp = builder.modifiedTimestamp;
        this.lastAccessTimestamp = builder.lastAccessTimestamp;
        this.isEnabled = builder.isEnabled;
        this.isLocked = builder.isLocked;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public Instant getCreatedTimestamp() {
        return createdTimestamp;
    }

    public ZonedDateTime getLastAccessTimestamp() {
        return lastAccessTimestamp;
    }

    public Instant getModifiedTimestamp() {
        return modifiedTimestamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean hasAuthority(Authority role) {
        return getUserGroup().hasAuthority(role);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getUserGroup()
                .getAuthorities()
                .stream()
                .map(Authority::toGrantedAuthority)
                .collect(Collectors.toSet());
    }

    public static final class Builder {

        private Long id;
        private String username;
        private String password;
        private boolean isEnabled;
        private boolean isLocked;
        private UserGroup userGroup;
        private Instant createdTimestamp;
        private ZonedDateTime lastAccessTimestamp;
        private String userAgent;
        private Instant modifiedTimestamp;

        private Builder() {
            isEnabled = false;
            isLocked = false;
        }

        private Builder(User user) {
            id = user.id;
            username = user.username;
            password = user.password;
            isEnabled = user.isEnabled;
            isLocked = user.isLocked;
            userGroup = user.userGroup;
            createdTimestamp = user.createdTimestamp;
            lastAccessTimestamp = user.getLastAccessTimestamp();
            modifiedTimestamp = user.modifiedTimestamp;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withIsEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        public Builder withIsLocked(boolean isLocked) {
            this.isLocked = isLocked;
            return this;
        }

        public Builder withUserGroup(UserGroup userGroup) {
            this.userGroup = userGroup;
            return this;
        }

        public Builder withCreatedTimestamp(Instant createdTimestamp) {
            this.createdTimestamp = createdTimestamp;
            return this;
        }

        public Builder withLastAccessTimestamp(ZonedDateTime lastAccessTimestamp) {
            this.lastAccessTimestamp = lastAccessTimestamp;
            return this;
        }

        public Builder withUserAgent(String userAgent) {
            this.userAgent = userAgent;
            return this;
        }

        public Builder withModifiedTimestamp(Instant modifiedTimestamp) {
            this.modifiedTimestamp = modifiedTimestamp;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
