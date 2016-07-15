package com.az.dev.JDBC.precompiledstatements.procedures;

import com.az.dev.JDBC.connection.MyConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by aziarkash on 14-7-2016.
 */
public class CallProcedure {

    public static void main(String[] args) {
        try (Connection connection = MyConnection.createConnection();
                CallableStatement callableStatement = connection.prepareCall("{call employee_details_new_age()}");
                ResultSet rs = callableStatement.executeQuery()) {
            while (rs.next()) {
                System.out.println(rs.getInt("age") + "--" + rs.getString("first"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
