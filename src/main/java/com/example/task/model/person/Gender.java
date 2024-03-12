package com.example.task.model.person;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Gender {
    M("M"),
    F("F");

    private final String displayName;
}
