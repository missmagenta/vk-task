--liquibase formatted sql

--changeset magenta:1_13.03.2024

CREATE TYPE request_status as ENUM ('SUCCEEDED', 'REJECTED');

CREATE TABLE IF NOT EXISTS "audit_log" (
                             "id" SERIAL PRIMARY KEY,
                             "username" VARCHAR(255) NOT NULL,
                             "request_url" TEXT NOT NULL,
                             "status" request_status NOT NULL,
                             "request_method" VARCHAR(50) NOT NULL,
                             "request_time" TIMESTAMP NOT NULL
);