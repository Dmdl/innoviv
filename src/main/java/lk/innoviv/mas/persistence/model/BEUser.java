package lk.innoviv.mas.persistence.model;

public class BEUser {

    private final Long id;
    private final String firstName;
    private final String lastName;
    private final User user;
    private final Long clientId;

    private BEUser(Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.user = builder.user;
        this.clientId = builder.clientId;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public User getUser() {
        return user;
    }

    public Long getClientId() {
        return clientId;
    }

    public static final class Builder {
        private Long id;
        private String firstName;
        private String lastName;
        private User user;
        private Long clientId;

        private Builder(BEUser beUser) {
            this.id = beUser.id;
            this.firstName = beUser.firstName;
            this.lastName = beUser.lastName;
            this.user = beUser.user;
            this.clientId = beUser.clientId;
        }

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }

        public Builder withClientId(Long clientId) {
            this.clientId = clientId;
            return this;
        }

        public BEUser build() {
            return new BEUser(this);
        }
    }

    @Override
    public int hashCode() {
        if (getId() == null) {
            return super.hashCode();
        }
        final int prime = 31;
        int result = 1;
        result = prime * result + getId().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof BEUser)) {
            return super.equals(other);
        }

        BEUser otherBeUser = (BEUser) other;
        if (getId() != null) {
            return getId().equals(otherBeUser.getId());
        } else {
            return super.equals(other);
        }
    }
}
