package com.example.task.mapper;

import com.example.task.model.person.Gender;
import com.example.task.model.person.Person;
import org.jooq.Record;
import org.springframework.stereotype.Component;

import static com.example.task.utils.TableAliases.PERSON_TABLE;
/**
 * Mapper class responsible for mapping jOOQ Records to domain-specific Person objects.
 * This mapper is used to convert database records into application-specific domain objects.
 */
@Component
public class PersonRecordMapper {

    public Person mapPerson(Record record) {
        return mapPerson(record, PERSON_TABLE);
    }

    public Person mapPerson(Record record, com.example.task.models.public_.tables.Person alias) {
        return new Person(
                record.get(alias.ID),
                record.get(alias.NAME),
                record.get(alias.SURNAME),
                record.get(alias.BIRTH_DATE),
                convertGender(record.get(alias.GENDER)),
                record.get(alias.EMAIL)
        );
    }

    public Gender convertGender(com.example.task.models.public_.enums.GenderEnum modelName) {
        return Gender.valueOf(modelName.name().toUpperCase());
    }
}