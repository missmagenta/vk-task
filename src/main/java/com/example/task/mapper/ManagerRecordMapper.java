package com.example.task.mapper;


import com.example.task.model.official.Manager;
import com.example.task.model.official.ManagerRole;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.example.task.utils.TableAliases.MANAGER_TABLE;

@Component
public class ManagerRecordMapper {

    public Manager mapManager(Record record) {
        return mapOfficial(record, MANAGER_TABLE);
    }


    private ManagerRole convertOfficialName(com.example.task.models.public_.enums.ManagerRole modelName) {
        for (ManagerRole value : ManagerRole.values()) {
            if (value.getDisplayRole().equals(modelName.name())) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid MyEnum id: " + modelName.name());
    }

    public Manager mapOfficial(Record record, com.example.task.models.public_.tables.Manager alias) {
        Manager official = new Manager();
        official.setId(record.get(alias.ID));
        official.setPersonId(record.get(alias.PERSON_ID));
        official.setManagerRole(convertOfficialName(record.get(alias.ROLE)));
        official.setEmploymentDate(record.get(alias.EMPLOYMENT_DATE));
        official.setFiredDate(record.get(alias.FIRED_DATE));

        return official;
    }
}

