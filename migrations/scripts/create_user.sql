--liquibase formatted sql

--changeset magenta:1_13.03.2024

create type USER_ROLE as ENUM ('ADMIN', 'POSTS', 'USERS', 'ALBUMS', 'INVALID');

CREATE TABLE IF NOT EXISTS "users" (
                         "id" SERIAL PRIMARY KEY,
                         "person_id" INTEGER NOT NULL UNIQUE,
                         "username" VARCHAR(255) NOT NULL,
                         "password" VARCHAR(255) NOT NULL,
                         "role" user_role NOT NULL,
                         FOREIGN KEY ("person_id") REFERENCES "person" ("id")
)