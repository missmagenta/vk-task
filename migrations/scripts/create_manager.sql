--liquibase formatted sql

--changeset magenta:1_13.03.2024

CREATE TYPE manager_role as ENUM('ADMIN', 'POSTS_MANAGER', 'USERS_MANAGER', 'ALBUMS_MANAGER');

CREATE TABLE IF NOT EXISTS "manager" (
                           "id" SERIAL PRIMARY KEY,
                           "role" manager_role NOT NULL,
                           "person_id" INTEGER NOT NULL,
                           "employment_date" TIMESTAMP NOT NULL,
                           "fired_date" TIMESTAMP,
                           FOREIGN KEY ("person_id") REFERENCES "person" ("id")
);