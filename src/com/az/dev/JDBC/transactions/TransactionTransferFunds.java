package com.az.dev.JDBC.transactions;

import com.az.dev.JDBC.connection.MyConnectionManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by aziarkash on 13-7-2016.
 */
public class TransactionTransferFunds {

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // create connection with database
            connection = MyConnectionManager.createConnection();
            // start transaction by calling setAutoCommit(false) on Connection object.
            connection.setAutoCommit(false);
            // create statement from connection object
            statement = connection.createStatement();
            // insert data in transaction table
            insertDataToTransactionTable(statement);
            // insert data in bank_account table
            insertDataToBankAccountTable(statement);
            // commit all changes to database
            connection.commit();
        } catch (SQLException e) {
            try {
                // If any exception is thrown, call rollback() on Connection object
                System.out.println("Caught an exception, rolling back the data ...");
                connection.rollback();
            } catch (SQLException sqlE) {
                System.out.println(sqlE);
            }
        } finally {
            try {
                // remove data inside tables : NOTE the autocommit is set to true
                connection.setAutoCommit(true);
                cleanUpTransactionAndBankAccountTables(statement);
                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }

    /**
     * Insert data in transaction table.
     *
     * @param statement
     * @throws SQLException
     */
    public static void insertDataToTransactionTable(final Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO transaction VALUES (1, '5555', 'debit', 55.0, '2016-07-13')");
        statement.executeUpdate("INSERT INTO transaction VALUES (2, '7777', 'credit', 55.0, '2016-07-13')");
    }

    /**
     * Insert data in bank_account table.
     *
     * @param statement
     * @throws SQLException
     */
    public static void insertDataToBankAccountTable(final Statement statement) throws SQLException {
        statement.executeUpdate("INSERT INTO bank_account VALUES (5555, 'A Ziarkash', 1000000)");
        statement.executeUpdate("INSERT INTO bank_account VALUES (7777, 'V Aggarwal', 10000)");
    }

    /**
     *
     * Update rows in transaction and bank_account tables
     *
     * Update balance for account_number 5555 and 7777 in table bank_account.
     *
     * @param statement
     * @throws SQLException
     */
    private void updateDataInTransactionAndBankDetails(final Statement statement) throws SQLException {
        statement.executeUpdate("UPDATE bank_account SET balance = 944.0 WHERE account_number = '5555'");
        statement.executeUpdate("UPDATE bank_account SET balance = 155.0 WHERE account_number = '7777'");
    }

    /**
     * Remove the data from the transaction and bank_account tables
     *
     * @param statement
     */
    public static void cleanUpTransactionAndBankAccountTables(final Statement statement) throws SQLException {
            statement.executeUpdate("DELETE FROM bank_account");
            statement.executeUpdate("DELETE FROM transaction");
    }
}
