package com.example.task.mapper;

import com.example.task.model.user.User;
import com.example.task.model.user.UserRole;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.example.task.utils.TableAliases.USER_TABLE;
/**
 * Mapper class responsible for mapping jOOQ Records to domain-specific User objects.
 * This mapper is used to convert database records into application-specific domain objects.
 */
@Component
public class UserRecordMapper {

    public User mapUser(Record record) {
        User user = new User();

        user.setId(record.get(USER_TABLE.ID));
        user.setPersonId(record.get(USER_TABLE.PERSON_ID));
        user.setUsername(record.get(USER_TABLE.USERNAME));
        user.setPassword(record.get(USER_TABLE.PASSWORD));
        user.setRole(convertRole(record.get(USER_TABLE.ROLE)));

        return user;
    }

    private static UserRole convertRole(com.example.task.models.public_.enums.UserRole role) {
        return UserRole.valueOf(role.name().toUpperCase());
    }
}

