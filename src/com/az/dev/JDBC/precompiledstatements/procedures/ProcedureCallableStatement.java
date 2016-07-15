package com.az.dev.JDBC.precompiledstatements.procedures;

import com.az.dev.JDBC.connection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by aziarkash on 14-7-2016.
 */
public class ProcedureCallableStatement {

    public static void main(String[] args) {
        try (Connection connection = MyConnection.createConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "CREATE PROCEDURE employee_details_new_age() "
                                + "BEGIN "
                                    + "SELECT A.id, A.first, B.age "
                                    + "FROM employees A, employees_update B "
                                    + "WHERE A.id = B.id; "
                                + "END;")) {
            int result = statement.executeUpdate();
            if (result == 0) {
                System.out.println(result);
                System.out.println("Creating procedure succeeded .");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
