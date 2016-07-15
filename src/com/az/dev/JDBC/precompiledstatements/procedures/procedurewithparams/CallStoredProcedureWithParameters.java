package com.az.dev.JDBC.precompiledstatements.procedures.procedurewithparams;

import com.az.dev.JDBC.connection.MyConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by aziarkash on 15-7-2016.
 */
public class CallStoredProcedureWithParameters {

    public static void main(String[] args) {

        try (Connection connection = MyConnection.createConnection();
                CallableStatement cs = connection.prepareCall("{call proc_author_row_count(?, ?)}")) {
            int rowCount = 10;
            String authorName = "Amin";

            cs.setString(1, authorName);
            cs.registerOutParameter(2, Types.NUMERIC);
            cs.setInt(2, rowCount);

            cs.execute();
            System.out.println("rowCount\t:\t" + rowCount);

        } catch (SQLException e) {
            System.out.println(e);
        }
    }
}
