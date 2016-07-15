package com.az.dev.JDBC.transactions;

import com.az.dev.JDBC.connection.MyConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

/**
 * Created by aziarkash on 13-7-2016.
 */
public class TransactionSavepoint {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // create a connection with the database
            connection = MyConnection.createConnection();
            // set auto-commit to false
            connection.setAutoCommit(false);
            // create statement
            statement = connection.createStatement();
            // insert data to transaction for account_number 1234
            statement.executeUpdate("INSERT INTO TRANSACTION VALUES (101, 1234, 'debit', 2099.5, '2016-08-13')");

            // insert data into bank_details table
            insertDataIntoBankDetails(statement);

            // update credit salary for account 1234
            statement.executeUpdate("UPDATE bank_account SET balance = balance + 2099.5 WHERE account_number = 1234");

            // set unnamed savepoint
            Savepoint sp1234 = connection.setSavepoint();

            // insert data to transaction for account_number 2244
            statement.executeUpdate("INSERT INTO transaction VALUES (102, '2244', 'debit', 2099.5, '2016-08-13')");
            // update credit salary for account_number 2244
            statement.executeUpdate("UPDATE bank_account SET balance = balance + 2099.5 WHERE account_number = '2244'");

            // set a named savepoint
            Savepoint sp2244 = connection.setSavepoint("CreditSalaryFor2244");

            // insert data to transaction for account_number 9999
            statement.executeUpdate("INSERT INTO transaction VALUES (103, 9999, 'debit', 5099.3, '2017-03-24')");
            // update credit salary for account_number 9999
            statement.executeUpdate("UPDATE bank_account SET balance = balance + 5099.3 WHERE account_number = 9999");

            // another unnamed savepoint
            Savepoint sp9999 = connection.setSavepoint("CreditSalaryFor9999");

            // rollback all transactions to savepoint sp2244
            connection.rollback(sp2244);

            // commit the transactions up to savepoint sp2244
            connection.commit();

        } catch (SQLException sqlEx1) {
            System.out.println(sqlEx1);
            try {
                // if SQLException, rollback all database changes
                connection.rollback();
            } catch (SQLException sqlEx2) {
                System.out.println(sqlEx2);
            }
        } finally {
            try {
                statement.executeUpdate("DELETE from bank_account");
                statement.executeUpdate("DELETE from transaction");
                connection.commit();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     *
     * Insert data into bank_detail table
     *
     * @param statement
     * @throws SQLException
     */
    private static void insertDataIntoBankDetails(final Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO bank_account VALUES (2244, 'Z Ziarkash', 8756)");
        statement.executeUpdate("INSERT INTO bank_account VALUES (9999, 'V Aggarwal', 133687)");
        statement.executeUpdate("INSERT INTO bank_account VALUES (1234, 'X Zhang', 32145)");
    }
}
