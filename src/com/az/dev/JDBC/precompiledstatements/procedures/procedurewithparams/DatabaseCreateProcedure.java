package com.az.dev.JDBC.precompiledstatements.procedures.procedurewithparams;

import com.az.dev.JDBC.connection.MyConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class creates a stored procedure with a parameter
 *
 * Created by aziarkash on 15-7-2016.
 */
public class DatabaseCreateProcedure {

    public static void main(String[] args) {
        try (Connection connection = MyConnection.createConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "CREATE PROCEDURE proc_book_count (OUT count INT)"
                            + "BEGIN "
                                + "SELECT COUNT(*) INTO count FROM book; "
                            + "END;");
            int result = statement.executeUpdate();
            System.out.println(result);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
