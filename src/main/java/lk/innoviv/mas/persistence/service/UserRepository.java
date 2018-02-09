package lk.innoviv.mas.persistence.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lk.innoviv.mas.exception.UnknownIdException;
import lk.innoviv.mas.persistence.model.User;
import lk.innoviv.mas.persistence.model.UserGroup;
import lk.innoviv.mas.persistence.model.UserGroup.Type;
import lk.innoviv.mas.security.model.Security;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;

import static lk.innoviv.mas.persistence.schema.Tables.*;

@Service
public class UserRepository {
    private Cache<Long, UserGroup> cachedUserGroupsById;
    private Cache<UserGroup.Type, UserGroup> cachedUserGroupsByType;

    private DSLContext context;

    @Autowired
    public UserRepository(DSLContext context) {
        this.context = context;
        this.cachedUserGroupsById = CacheBuilder.newBuilder().build();
        this.cachedUserGroupsByType = CacheBuilder.newBuilder().build();
    }

    public User getUser(String username) {
        Record record = fetchUser(USERS.USER_NAME.eq(username));
        if (record == null) {
            throw new UnknownIdException(username, "Could not find user.");
        }
        UserGroup userGroup = getUserGroup(record.get(USERS.USERGROUPID).longValue());
        User.Builder user = toBuilder(record, userGroup);
        return user.build();
    }

    private Record fetchUser(Condition condition) {
        return context.select()
                .from(USERS)
                .where(condition)
                .fetchOne();
    }

    UserGroup getUserGroup(Long id) {
        try {
            return cachedUserGroupsById.get(id, () -> {
                UserGroup userGroup = fetchUserGroup(id);
                cachedUserGroupsByType.put(userGroup.getType(), userGroup);
                return userGroup;
            });
        } catch (ExecutionException e) {
            throw new UnknownIdException(String.valueOf(id), "Could not find user group.");
        }
    }

    private UserGroup fetchUserGroup(Long id) {
        SortedSet<Type> types = new TreeSet<>();
        List<Security.Authority> fetched = context.select()
                .from(USERGROUP)
                .join(USERGROUPAUTHORITY)
                .on(USERGROUPAUTHORITY.USERGROUPID.eq(USERGROUP.USERGROUPID))
                .and(USERGROUP.USERGROUPID.eq(id.intValue()))
                .fetch()
                .map(record -> {
                    Security.Authority authority = Security.Authority.valueOf(record.get(USERGROUPAUTHORITY.AUTHORITY));
                    types.add(UserGroup.Type.valueOf(record.get(USERGROUP.NAME)));
                    return authority;
                });
        if (fetched.isEmpty()) {
            throw new UnknownIdException(String.valueOf(id), "Could not find user group.");
        }
        return UserGroup.builder()
                .withId(id)
                .withName(types.first())
                .withAuthorities(fetched)
                .build();
    }

    private static User.Builder toBuilder(Record record, UserGroup userGroup) {

        Timestamp modified = record.get(USERS.MODIFIEDTIMESTAMP);
        return User.builder()
                .withId(record.get(USERS.USER_ID).longValue())
                .withUsername(record.get(USERS.USER_NAME))
                .withPassword(record.get(USERS.PASSWORD))
                .withIsEnabled(record.get(USERS.ISENABLED))
                .withCreatedTimestamp(record.get(USERS.CREATEDTIMESTAMP).toInstant())
                .withModifiedTimestamp(modified != null ? modified.toInstant() : null)
                .withUserGroup(userGroup);
    }
}
