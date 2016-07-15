package com.az.dev.JDBC.precompiledstatements;

import com.az.dev.JDBC.connection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by aziarkash on 14-7-2016.
 */
public class QueryEmployeesPreparedStatement {

    public static void main(String[] args) {
        try (Connection connection = MyConnection.createConnection();
                PreparedStatement stmt = connection
                        .prepareStatement("SELECT * FROM employees WHERE age > 24");    // NOTICE the query is done on object creation
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Name\t:\t" + rs.getString("first") + " " + rs.getString("last"));
                System.out.println("Age\t\t:\t" + rs.getInt("age"));
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
