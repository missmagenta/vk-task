package com.example.task.model.official;

import com.example.task.model.person.Person;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Manager {
    private Integer id;
    private Integer personId;
    private ManagerRole managerRole;
    private LocalDateTime employmentDate;
    private LocalDateTime firedDate;
    private Person person;
}
