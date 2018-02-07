package lk.innoviv.mas.persistence.service;

import lk.innoviv.mas.persistence.model.User;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static lk.innoviv.mas.persistence.schema.Tables.*;

@Service
public class UserRepository {

    @Autowired
    DSLContext context;

    @Transactional
    public void create() {
        context.insertInto(USERS)
                .set(USERS.USER_NAME, "test")
                .set(USERS.PASSWORD, "test").execute();
    }

    @Transactional
    public List<User> getUsers() {
        return context.select().from(USERS).fetch().map(UserRepository::toUser);
    }

    private static User toUser(Record record) {
        User user = new User();
        user.setId(record.get(USERS.USER_ID, Long.class));
        user.setUsername(record.get(USERS.USER_NAME));
        user.setPassword(record.get(USERS.PASSWORD));
        return user;
    }
}
