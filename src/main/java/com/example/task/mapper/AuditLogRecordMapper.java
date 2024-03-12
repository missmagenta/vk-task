package com.example.task.mapper;

import com.example.task.model.audit.LogRecord;
import com.example.task.model.audit.Status;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.example.task.utils.TableAliases.AUDIT_LOG_TABLE;

@Component
public class AuditLogRecordMapper {
    public LogRecord mapLog(Record record) {
        return mapLog(record, AUDIT_LOG_TABLE);
    }

    public LogRecord mapLog(Record record, com.example.task.models.public_.tables.AuditLog alias) {
        return new LogRecord(
                record.get(alias.ID),
                record.get(alias.USERNAME),
                record.get(alias.REQUEST_TIME),
                record.get(alias.REQUEST_URL),
                record.get(alias.REQUEST_METHOD),
                convertStatus(record.get(alias.STATUS))
        );
    }

    public Status convertStatus(com.example.task.models.public_.enums.RequestStatus modelName) {
        return Status.valueOf(modelName.name().toUpperCase());
    }
}
