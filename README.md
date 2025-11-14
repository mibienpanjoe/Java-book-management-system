# Book Management Desktop App

A simple Java 8 desktop application for managing a list of books. The UI is built with Swing and the data is persisted locally using SQLite. The app supports user login and provides CRUD operations for books.

## Table of Contents
- Overview
- Key Features
- Architecture and Project Structure
- Class Responsibilities
- Database
- Getting Started
- Running the Application
- Troubleshooting
- Extending the Application

---

## Overview
This application demonstrates a straightforward desktop workflow:
1. User logs in with a username and password.
2. After a successful login, the Book Management screen opens.
3. Users can add, update, delete, and view books stored in a local SQLite database.

The project targets Java SDK 8 and can be run from an IDE or from the command line.

---

## Key Features
- Login screen with credential verification against a local database.
- Book management screen with:
  - Add a new book.
  - Update an existing book by title.
  - Delete a book by title.
  - View all books in a formatted, scrollable view.
- Input validation:
  - Non-empty title/author/year.
  - Year must be numeric and within a reasonable range (e.g., 1000–2100).
- Friendly user feedback via dialogs for success, validation errors, and database issues.

---

## Architecture and Project Structure
A high-level breakdown of the main components:
- Entry point to start the application and open the Login UI.
- A Login UI for authenticating users.
- A Book Management UI for performing CRUD operations on books.
- A lightweight database access utility for obtaining connections.
- A database initializer utility to create tables and seed sample data.

Typical layout:
- src/main/java/... contains the application source code organized by package.
- A local SQLite database file is used to store users and books.

---

## Class Responsibilities
- Main
  - Starts the application by showing the login screen.
- LoginForm
  - Presents username and password fields and a login button.
  - Authenticates the user by verifying credentials against the database.
  - On successful login, opens the book management screen.
- BookManagementForm
  - Provides text fields and buttons for book title, author, and year.
  - Implements add, update, delete, and view functionalities.
  - Validates user input and communicates outcomes via dialogs.
- DatabaseConnection
  - Centralized utility for obtaining a JDBC connection to the local SQLite database file.
- DatabaseInitializer
  - Creates the required tables if they don’t exist.
  - Seeds the database with sample users and books to get started quickly.

---

## Database
- Engine: SQLite, stored as a local file next to the project.
- Tables:
  - users(username, password)
  - books(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT NOT NULL, author TEXT NOT NULL, year INTEGER NOT NULL)
- A database initializer utility exists to create tables and insert sample data.

Notes:
- Titles are used for update/delete matching in the current UX for simplicity.
- For production-grade systems, operations should rely on immutable IDs selected from a list/table.

---

## Getting Started

Prerequisites:
- Java 8 (JDK 1.8)
- An IDE like IntelliJ IDEA (recommended) or Maven (optional) if running from the command line.

Dependencies:
- SQLite JDBC driver must be available on the classpath.
  - If using Maven, add the SQLite JDBC dependency (org.xerial:sqlite-jdbc) to your build.
  - If using a local JAR, ensure it’s present on the run classpath.

Recommended IDE flow:
1. Open the project in IntelliJ IDEA.
2. Ensure Java SDK 8 is configured for the project.
3. Run the database initializer (see below).
4. Run the main application entry point.

---

## Running the Application

1) Initialize the database (one-time or whenever you want to reset sample data):
- Run the database initialization utility. It will:
  - Create the users and books tables if they don’t exist.
  - Insert sample records (e.g., an admin user and a few example books).

2) Start the app:
- Run the main entry point. The Login screen should appear.

3) Login:
- Use the sample credentials inserted by the initializer or create your own.

4) Manage books:
- After login, use the management screen to add, update, delete, and view books.

Command line (optional, if you prefer):
- If using Maven, compile the project:
  - mvn clean compile
- Run with the appropriate classpath including your SQLite JDBC driver.

---

## Troubleshooting
- I get a “cannot find driver” or JDBC errors:
  - Ensure the SQLite JDBC driver is available on the classpath.
  - Verify your Java version is 8 and matches the project configuration.
- I see validation warnings:
  - Check that all required fields are filled and the year is numeric within the accepted range.
- No books appear in the list:
  - Ensure you ran the database initializer.
  - Check the database file path and write permissions.

---

## Extending the Application
- Improve security:
  - Hash and salt passwords instead of storing them in plain text.
- Use IDs for edits/deletes:
  - Replace title-based updates/deletes with ID-based operations via a selectable list/table.
- Enhance the UI:
  - Use JTable for listing books with sorting and selection.
  - Add input masks or spinners for year input.

---

## License
This project is provided as-is for educational purposes. Adapt and extend as needed for your use case.

#  Mj
