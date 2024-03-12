package com.example.task.repository;

import com.example.task.mapper.ManagerRecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;


import com.example.task.model.official.Manager;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import static com.example.task.utils.TableAliases.MANAGER_TABLE;

@RequiredArgsConstructor
@Repository
public class ManagerRepository {
    private final DSLContext dsl;
    private final ManagerRecordMapper officialRecordMapper;


    @Transactional(readOnly = true)
    public Manager getCurrentByPersonId(Integer personId) {
        return dsl.selectFrom(MANAGER_TABLE)
                .where(MANAGER_TABLE.PERSON_ID.eq(personId))
                .and(MANAGER_TABLE.FIRED_DATE.isNull())
                .fetchOptional()
                .map(officialRecordMapper::mapManager)
                .orElse(null);
    }
}
