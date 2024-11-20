/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.user2310214.repository;
import java.sql.*;
import com.user2310214.model.Book;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author tracy
 */
public class BookRepository {
    private final String url;
    private final String user;
    private final String password;

    public BookRepository(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public void save(Book book) {
        String sql = "INSERT INTO books (title, author, genre, year) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setInt(4, book.getYear());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error saving book", e);
        }
    }

    
    public List<Book> findByTitle( String title) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                books.add(new Book.BookBuilder()
                        .setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setAuthor(resultSet.getString("author"))
                        .setGenre(resultSet.getString("genre"))
                        .setYear(resultSet.getInt("year"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving books", e);
        }
        return books;
    }
    
    public List<Book> findByAuthor( String autor) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                books.add(new Book.BookBuilder()
                        .setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setAuthor(resultSet.getString("author"))
                        .setGenre(resultSet.getString("genre"))
                        .setYear(resultSet.getInt("year"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving books", e);
        }
        return books;
    }
            
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                books.add(new Book.BookBuilder()
                        .setId(resultSet.getInt("id"))
                        .setTitle(resultSet.getString("title"))
                        .setAuthor(resultSet.getString("author"))
                        .setGenre(resultSet.getString("genre"))
                        .setYear(resultSet.getInt("year"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving books", e);
        }
        return books;
    }

    public void update(Book book) {
        String sql = "UPDATE books SET title = ?, author = ?, genre = ?, year = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book.getTitle());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getGenre());
            statement.setInt(4, book.getYear());
            statement.setInt(5, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating book", e);
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting book", e);
        }
    }
}