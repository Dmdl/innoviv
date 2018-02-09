package lk.innoviv.mas.persistence.model;

public class Admin {
    private final User user;

    public Admin(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
