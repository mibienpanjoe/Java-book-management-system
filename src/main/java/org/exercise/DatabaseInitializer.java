package org.exercise;

//Here we can initialize the database and create sample data
import java.sql.*;

public class DatabaseInitializer {
    public static void main(String[] args) {
        initializeDatabase();
    }

    public static void initializeDatabase() {
        String url = "jdbc:sqlite:book_management.db";
        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            if (conn != null) {
                System.out.println("Connected to database");

                // Create a user's table
                String createUserTable = "CREATE TABLE IF NOT EXISTS users(" +
                        "username VARCHAR(255), " +
                        "password VARCHAR(255))";
                stmt.executeUpdate(createUserTable);
                System.out.println("User table created");

                // Insert dummy data
                String insertUser = "INSERT INTO users VALUES ('admin', 'admin1234')";
                stmt.executeUpdate(insertUser);
                System.out.println("Dummy data inserted");

                // Create a books table - FIXED VERSION
                String createBooksTable = "CREATE TABLE IF NOT EXISTS books(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "title TEXT NOT NULL, " +  // Added comma and fixed NOT NULL
                        "author TEXT NOT NULL, " + // Added comma and fixed NOT NULL
                        "year INTEGER NOT NULL" +  // Fixed NOT NULL
                        ")";
                stmt.executeUpdate(createBooksTable);
                System.out.println("Books table created");

                // Insert dummy data - FIXED INSERT STATEMENTS
                String insertBook1 = "INSERT INTO books (title, author, year) VALUES ('The Hobbit', 'J.R.R. Tolkien', 1937)";
                String insertBook2 = "INSERT INTO books (title, author, year) VALUES ('The Lord of the Rings', 'J.R.R. Tolkien', 1954)";
                String insertBook3 = "INSERT INTO books (title, author, year) VALUES ('The Hunger Games', 'Suzanne Collins', 2008)";

                stmt.executeUpdate(insertBook1);
                stmt.executeUpdate(insertBook2);
                stmt.executeUpdate(insertBook3);
                System.out.println("Dummy data inserted Successfully");
            }

        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}