package com.az.dev.JDBC.precompiledstatements;

import com.az.dev.JDBC.connection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by aziarkash on 14-7-2016.
 */
public class UpdateEmployeesPreparedStatement {

    public static void main(String[] args) {
        try (Connection connection = MyConnection.createConnection();
                PreparedStatement empUpdateStmt = connection
                        .prepareStatement("UPDATE employees SET age = ? WHERE id = ?"); // NOTICE the parameters '?'
                PreparedStatement newEmpStmt = connection
                        .prepareStatement("SELECT id, age FROM employees_update")) {

            ResultSet empNewAgeRs = newEmpStmt.executeQuery();

            while (empNewAgeRs.next()) {
                empUpdateStmt.setInt(1, empNewAgeRs.getInt("age"));
                empUpdateStmt.setInt(2, empNewAgeRs.getInt("id"));
                empUpdateStmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
