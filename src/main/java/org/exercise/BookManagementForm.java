package org.exercise;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookManagementForm extends JFrame {

    //Interface components
    JTextField yearField, authorField, titleField;
    private JButton addButton , updateButton , deleteButton, viewButton;

    // constructor
    public BookManagementForm() {

        //Management frame layout
        setTitle("Book Management Form");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel info
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        //Components creation
        titleField = new JTextField();
        authorField = new JTextField();
        yearField = new JTextField();
            // Setting inputs width
        titleField.setColumns(20);
        authorField.setColumns(20);
        yearField.setColumns(20);

        addButton = new JButton("Add Book");
        updateButton = new JButton("Update Book");
        deleteButton = new JButton("Delete Book");
        viewButton = new JButton("View Books");

        //Adding the created components in the Panel
            // Here It is like we define first the input tile eith a label , and after we have the input itself
        panel.add(new  JLabel("Title"));
        panel.add(titleField);
        panel.add(new  JLabel("Author"));
        panel.add(authorField);
        panel.add(new  JLabel("Year"));
        panel.add(yearField);

        panel.add(addButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        panel.add(viewButton);

        //Events Management logic
        addButton.addActionListener((ActionListener)new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBook();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewBooks();
            }
        });
        add(panel);

    }

  // CRUD implementation

    //Add books
    private void addBook() {
        // getting information for the inputs ad storing them in variables
        String title = titleField.getText();
        String author = authorField.getText();
        int year = Integer.parseInt(yearField.getText());

        try (Connection conn = DatabaseConnection.getConnection()){
            String sql = "INSERT INTO Books (Title, Author, Year) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setInt(3, year);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book added successfully"); //alert
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
    //Update books
    private  void updateBook(){
        String title = titleField.getText();
        String author = authorField.getText();
        int year = Integer.parseInt(yearField.getText());

        try (Connection conn = DatabaseConnection.getConnection()){
            String sql = "UPDATE books SET author = ? , year = ? WHERE Title = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, author);
            stmt.setInt(2, year);
            stmt.setString(3, title);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book updated successfully"); //alert
        } catch (SQLException ex) {
            ex.printStackTrace();
    }}


    //Delete books
    private void deleteBook() {
        String title = titleField.getText();

        try (Connection conn = DatabaseConnection.getConnection()){
            String sql = "DELETE FROM books WHERE title = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, title);
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Book deleted successfully");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // View books
    // Improved View books method
    private void viewBooks() {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM books");
             ResultSet rs = stmt.executeQuery()) {

            StringBuilder books = new StringBuilder();

            // Check if any books exist
            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(this,
                        "No books found in the database",
                        "Information",
                        JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // Build the book list
            while (rs.next()) {
                books.append("ID: ").append(rs.getInt("id"))
                        .append(", Title: ").append(rs.getString("title"))
                        .append(", Author: ").append(rs.getString("author"))
                        .append(", Year: ").append(rs.getInt("year"))
                        .append("\n");
            }

            // Display in a scrollable dialog for better UX
            JTextArea textArea = new JTextArea(books.toString());
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(500, 300));

            JOptionPane.showMessageDialog(this,
                    scrollPane,
                    "Books List",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException ex) {
            // Better error handling
            JOptionPane.showMessageDialog(this,
                    "Error retrieving books: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

}
