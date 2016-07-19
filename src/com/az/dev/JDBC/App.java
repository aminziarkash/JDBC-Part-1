package com.az.dev.JDBC;

import com.az.dev.JDBC.CRUD.MyTable;
import com.az.dev.JDBC.connection.MyConnectionManager;

import java.sql.*;

/**
 * Created by aziarkash on 7-7-2016.
 */
public class App {

    private String objectiveString;

    public static void main(String[] args) {

        App app = new App();

        app.createConnectionAndGetEmployees();

        app.createTenEmployees();

        app.createTable();

        app.insertRowInATable();

        app.updateDataInTable();

        app.deleteRowFromTable();

        app.executeQuery();

        app.cleanUpDatabase();

    }

    private void cleanUpDatabase() {
        objectiveString = "Deleting Employee objects and Organization table";
        System.out.println("\033[1m" + objectiveString + "\033[0m");
        addSub(objectiveString);

        try (Connection connection = MyConnectionManager.createConnection();
                Statement statement = connection.createStatement()){

            String sql = "DELETE FROM employees WHERE id IN (999,0,1,2,3,4,5,6,7,8);";

            int emp = statement.executeUpdate(sql);
            int org = statement.executeUpdate("DROP TABLE `emp`.`organization`;");
            System.out.println("Number of employees deleted\t:\t" + emp);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        addSeparator();
    }

    private void createTenEmployees() {
        objectiveString = "Creating 10 employees";
        System.out.println("\033[1m" + objectiveString + "\033[0m");
        addSub(objectiveString);

        try (Connection connection = MyConnectionManager.createConnection();
                Statement statement = connection.createStatement()) {

            for (int i = 0; i < 10; i++) {
                String sql = "INSERT INTO employees VALUES (" + i + ",'" + 27 + "'," + "'Amin" + i + "'," + "'Ziarkash');";
                statement.executeUpdate(sql);
            }

            ResultSet rs = statement.executeQuery("SELECT id, first, last, age FROM Employees;");
            while (rs.next()) {
                // Retrieve by column name
                int id = rs.getInt("id");
                int age = rs.getInt("age");
                String first = rs.getString("first");
                String last = rs.getString("last");

                // Display values
                System.out.println("ID\t\t\t:\t" + id);
                System.out.println("AGE\t\t\t:\t" + age);
                System.out.println("FIRSTNAME\t:\t" + first);
                System.out.println("LASTNAME\t:\t" + last);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addSeparator();
    }

    private void deleteRowFromTable() {
        objectiveString = "Deleting rows from table";
        System.out.println("\033[1m" + objectiveString + "\033[0m");
        addSub(objectiveString);

        try (Connection connection = MyConnectionManager.createConnection();
                Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM employees WHERE first = 'Amin9';");     //
            System.out.println("\nDeleted employee 'Amin9'\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addSeparator();
    }

    private void executeQuery() {
        objectiveString = "Execute query method";
        System.out.println("\033[1m" + objectiveString + "\033[0m");
        addSub(objectiveString);

        try (Connection connection = MyConnectionManager.createConnection();
                Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM book;");     //
            while (rs.next()) {
                System.out.println("Found book\t:\t" + rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        addSeparator();
    }

    private void updateDataInTable() {
        objectiveString = "Update Data In Table";
        System.out.println("\033[1m" + objectiveString + "\033[0m");
        addSub(objectiveString);

        MyTable myTable = new MyTable();
        myTable.updateDataInTable();

        addSeparator();
    }

    private void insertRowInATable() {
        objectiveString = "Insert row in the Employees table";
        System.out.println("\033[1m" + objectiveString + "\033[0m");
        addSub(objectiveString);

        MyTable myTable = new MyTable();
        try {
            myTable.insertIntoTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addSeparator();
    }

    private void createTable() {
        objectiveString = "Create table";
        System.out.println("\033[1m" + objectiveString + "\033[0m");
        addSub(objectiveString);

        MyTable myTable = new MyTable();
        try{
            myTable.createTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addSeparator();
    }

    private void createConnectionAndGetEmployees() {
        objectiveString = "Create a connection with the database and get the list of employees";
        System.out.println("\033[1m" + objectiveString + "\033[0m");
        addSub(objectiveString);

        // create the connection and get employees
        try (Connection connection = MyConnectionManager.createConnection()) {
            MyConnectionManager connectionManager = new MyConnectionManager();
            connectionManager.getEmployees(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addSeparator();
    }

    public void addSub(String objectiveString) {
        for (int i = 0; i < objectiveString.length(); i++) {
            System.out.print("-");
        }
        System.out.println("\n");
    }

    public void addSeparator() {
        System.out.println("\n\033[1m**************************************************************************************************************\033[0m");
    }
}
