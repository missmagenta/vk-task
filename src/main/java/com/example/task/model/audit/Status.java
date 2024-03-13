package com.example.task.model.audit;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {
    SUCCEEDED("SUCCEEDED"),
    REJECTED("REJECTED");

    private final String displayStatus;
}