package com.az.dev.JDBC.CRUD;

import com.az.dev.JDBC.connection.MyConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by aziarkash on 8-7-2016.
 */
public class MyTable {

    public void createTable() throws SQLException {
        Connection connection = null;
        try {
            connection = MyConnectionManager.createConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Statement statement = connection.createStatement();

        int result = statement.executeUpdate("CREATE TABLE organization " +
                                                " (id INT PRIMARY KEY, " +
                                                " name VARCHAR(255)) ");
        System.out.println("Created table Organization");

        statement.close();
    }

    public void insertIntoTable() throws SQLException {
        try (Connection conn = MyConnectionManager.createConnection();
                Statement stmt = conn.createStatement()) {
            try {
                int ret = stmt.executeUpdate("INSERT INTO employees VALUES (999, '99999', 'ABCD', 'EFGH')");
                if (ret == 1) {
                    System.out.println("Inserting employee 'ABCD' succeeded!");
                }
            } catch (SQLException e) {
                System.out.println("The Employee you are trying to insert is already in the database!");
                e.printStackTrace();
            }
        }
    }

    public void updateDataInTable() {
        try (Connection conn = MyConnectionManager.createConnection();
                Statement stmt = conn.createStatement()) {
            int ret = stmt.executeUpdate("UPDATE employees SET first = 'Amin22222' WHERE id = 1;");
            if (ret == 1) {
                System.out.println("Updating first name for employee with id=1 succeeded!");
            }
        } catch (SQLException e) {
            System.out.println("Updating encountered a problem");
            e.printStackTrace();
        }
    }

}
