package com.example.task.mapper;


import com.example.task.model.official.Manager;
import com.example.task.model.official.ManagerRole;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.example.task.utils.TableAliases.MANAGER_TABLE;

@Component
public class ManagerRecordMapper {

    public Manager mapManager(Record record) {
        return mapManager(record, MANAGER_TABLE);
    }


    private ManagerRole convertManagerRole(com.example.task.models.public_.enums.ManagerRole modelName) {
        for (ManagerRole value : ManagerRole.values()) {
            if (value.getDisplayRole().equals(modelName.name())) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid MyEnum id: " + modelName.name());
    }

    public Manager mapManager(Record record, com.example.task.models.public_.tables.Manager alias) {
        Manager manager = new Manager();
        manager.setId(record.get(alias.ID));
        manager.setPersonId(record.get(alias.PERSON_ID));
        manager.setManagerRole(convertManagerRole(record.get(alias.ROLE)));
        manager.setEmploymentDate(record.get(alias.EMPLOYMENT_DATE));
        manager.setFiredDate(record.get(alias.FIRED_DATE));

        return manager;
    }
}

