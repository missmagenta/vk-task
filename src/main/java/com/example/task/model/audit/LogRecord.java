package com.example.task.model.audit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class LogRecord {
    private Integer id;
    private String username;
    private LocalDateTime requestDateTime;
    private String requestUrl;
    private String method;
    private Status status;
}