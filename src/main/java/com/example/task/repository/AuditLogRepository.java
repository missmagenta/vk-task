package com.example.task.repository;

import com.example.task.mapper.AuditLogRecordMapper;
import com.example.task.model.audit.LogRecord;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.task.utils.TableAliases.AUDIT_LOG_TABLE;

@Repository
public class AuditLogRepository {
    private final DSLContext dsl;
    private final AuditLogRecordMapper auditLogRecordMapper;

    @Autowired
    public AuditLogRepository(DSLContext dsl,
                          AuditLogRecordMapper auditLogRecordMapper) {
        this.dsl = dsl;
        this.auditLogRecordMapper = auditLogRecordMapper;
    }

    @Transactional
    public LogRecord insert(LogRecord logRecord) {
        return dsl.insertInto(AUDIT_LOG_TABLE)
                .set(dsl.newRecord(AUDIT_LOG_TABLE, logRecord))
                .returning()
                .fetchOptional()
                .map(auditLogRecordMapper::mapLog)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public LogRecord find(Integer id) {
        return dsl.selectFrom(AUDIT_LOG_TABLE)
                .where(AUDIT_LOG_TABLE.ID.eq(id))
                .fetchOptional()
                .map(auditLogRecordMapper::mapLog)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public LogRecord findByUsername(String username) {
        return dsl.selectFrom(AUDIT_LOG_TABLE)
                .where(AUDIT_LOG_TABLE.USERNAME.eq(username))
                .fetchOptional()
                .map(auditLogRecordMapper::mapLog)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<LogRecord> findAll(Condition condition) {
        return dsl.selectFrom(AUDIT_LOG_TABLE)
                .where(condition)
                .fetch()
                .map(auditLogRecordMapper::mapLog);
    }
}
