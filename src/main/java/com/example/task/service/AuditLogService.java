package com.example.task.service;

import com.example.task.model.audit.LogRecord;
import com.example.task.model.audit.Status;
import com.example.task.repository.AuditLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditLogService {

    private final HttpServletRequest request;

    private final AuditLogRepository auditLogRepository;

    @Autowired
    public AuditLogService(HttpServletRequest request, AuditLogRepository auditLogRepository) {
        this.request = request;
        this.auditLogRepository = auditLogRepository;
    }

    public void logRequest(Status status) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        LocalDateTime requestDateTime = LocalDateTime.now();

        LogRecord logRecord = LogRecord.builder()
                .username(username)
                .requestDateTime(requestDateTime)
                .requestUrl(requestUrl)
                .method(method)
                .status(status)
                .build();

        auditLogRepository.insert(logRecord);
    }
}

