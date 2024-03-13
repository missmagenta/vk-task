--liquibase formatted sql

--changeset magenta:1_13.03.2024

CREATE TYPE gender_enum as ENUM ('M', 'F');

CREATE TABLE IF NOT EXISTS "person" (
                          "id" SERIAL PRIMARY KEY,
                          "name" VARCHAR(255) NOT NULL,
                          "surname" VARCHAR(255) NOT NULL,
                          "birth_date" DATE NOT NULL,
                          "gender" gender_enum NOT NULL,
                          "email" TEXT NOT NULL
);