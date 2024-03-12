package com.example.task.model.person;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Person {
    private Integer id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Gender gender;
    private String email;
}
