package com.az.dev.JDBC.rowset;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

/**
 * Created by aziarkash on 14-7-2016.
 */
public class RowSetExample {

    public static void main(String[] args) {
        JdbcRowSet jdbcRowSet = null;

        try {
            // Register JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");

            RowSetFactory rowSetFactory = RowSetProvider.newFactory();
            jdbcRowSet = rowSetFactory.createJdbcRowSet();

            jdbcRowSet.setUrl("jdbc:mysql://localhost:3306/EMP");
            jdbcRowSet.setUsername("root");
            jdbcRowSet.setPassword("99STM15");
            jdbcRowSet.setCommand("SELECT * FROM employees");
            jdbcRowSet.execute();

            while (jdbcRowSet.next()) {
                System.out.println("First name\t:\t" + jdbcRowSet.getString("first"));
                System.out.println("Last name\t:\t" + jdbcRowSet.getString("last"));
                System.out.println();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        } finally {
            try {
                jdbcRowSet.close();
            } catch (SQLException e1) {
                System.out.println(e1);
            }
        }
    }
}
