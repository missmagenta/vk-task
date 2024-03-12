package com.example.task.utils;

import com.example.task.models.public_.tables.AuditLog;
import com.example.task.models.public_.tables.Manager;
import com.example.task.models.public_.tables.Person;
import com.example.task.models.public_.tables.Users;

import static com.example.task.utils.Aliases.*;

public class TableAliases {
    public static final Users USER_TABLE = Users.USERS.as(USER_ALIAS);
    public static final Person PERSON_TABLE = Person.PERSON.as(PERSON_ALIAS);
    public static final Manager MANAGER_TABLE = Manager.MANAGER.as(MANAGER_ALIAS);

    public static final AuditLog AUDIT_LOG_TABLE = AuditLog.AUDIT_LOG.as(AUDIT_LOG_ALIAS);
}
