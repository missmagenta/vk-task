package com.example.task.db;

import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MigrationTest extends IntegrationEnvironment {

    @Test
    void testTablesCreation() throws SQLException {
        assertAll(
                () -> assertTrue(tableExists("person")),
                () -> assertTrue(tableExists("manager")),
                () -> assertTrue(tableExists("user")),
                () -> assertTrue(tableExists("audit_log"))
        );
    }


    public static boolean tableExists(String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet tables = metaData.getTables(null, null, tableName, null)) {
            return tables.next();
        }
    }
}