package com.az.dev.JDBC.connection;

// STEP 1. Import required packages
import java.sql.*;
import java.util.Properties;

/**
 * Created by aziarkash on 7-7-2016.
 */
public class MyConnection {

    // Database URL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/EMP";

    // Database credentials
    private static final String USER = "root";
    private static final String PASS= "99STM15";

    private static Connection conn = null;
    private static Statement stmt = null;

    public static Connection createConnection() throws SQLException {
        try {
            // STEP 2. Register JDBC Driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3. Open a connection using DriverManager
            System.out.println("\nConnected to the database ... \n");

            // 1.
            // conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 2. notice the '?' after db name and '&' between username and password
            // conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EMP?" + "user=root&password=99STM15");

            // 3. Using properties for login credentials
            Properties properties = new Properties();
            properties.put("user", "root");
            properties.put("password", "99STM15");
            conn = DriverManager.getConnection(DB_URL, properties);
        } catch (SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void getEmployees(Connection conn) throws SQLException {
        // STEP 4. Execute a query
        System.out.println("Creating statement ... \n");
        stmt = conn.createStatement();
        String sql = "SELECT id, first, last, age FROM Employees";
        System.out.println("Statement\t:\t" + sql + "\n");
        ResultSet rs = stmt.executeQuery(sql);

        // STEP 5. Extract data from Result Set
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

        System.out.println("Connection object\t:\t" + conn);
        System.out.println("ResultSet object\t:\t" + rs);
        System.out.println("Statement object\t:\t" + stmt + "\n");

        // STEP 6. Clean-up environment
        rs.close();
        stmt.close();
        conn.close();

        System.out.println("Goodbye");
    }
}
