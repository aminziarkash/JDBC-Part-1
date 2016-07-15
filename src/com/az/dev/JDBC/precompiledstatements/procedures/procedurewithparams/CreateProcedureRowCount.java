package com.az.dev.JDBC.precompiledstatements.procedures.procedurewithparams;

import com.az.dev.JDBC.connection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by aziarkash on 15-7-2016.
 */
public class CreateProcedureRowCount {

    public static void main(String[] args) {
        try (Connection connection = MyConnection.createConnection()) {
            PreparedStatement statement = connection.prepareStatement
                    ("CREATE PROCEDURE proc_author_row_count (IN author_name CHAR(50), OUT count INT) "+
                            "BEGIN "+
                                "SELECT COUNT(*) INTO count FROM book " +
                                "WHERE author = author_name; " +
                            "END;");
            int result = statement.executeUpdate();
            System.out.println(result);
        }catch (SQLException e) {
            System.out.println(e);
        }
    }
}
