/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.user2310214.view;

import com.user2310214.service.BookService;
import com.user2310214.model.Book;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 *
 * @author tracy
 */
public class LibraryUI extends JFrame{
private final BookService bookService;

    public LibraryUI(BookService bookService) {
        this.bookService = bookService;
        initUI();
    }

    private void initUI() {
        setTitle("Library Management System");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Pestaña "Agregar libro"
        tabbedPane.add("Add Book", createAddBookPanel());

        // Pestaña "Buscar libro"
        tabbedPane.add("Search Books", createSearchBooksPanel());
        

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createAddBookPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        // Campos de entrada
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        JTextField yearField = new JTextField();

        // Botón para guardar
        JButton saveButton = new JButton("Save Book");
        JLabel messageLabel = new JLabel("", JLabel.CENTER);

        saveButton.addActionListener(e -> {
            String title = titleField.getText();
            String author = authorField.getText();
            String genre = genreField.getText();
            int year;

            try {
                year = Integer.parseInt(yearField.getText());
                bookService.addBook(title, author, genre, year);
                messageLabel.setText("Book added successfully!");
            } catch (NumberFormatException ex) {
                messageLabel.setText("Year must be a number!");
            } catch (IllegalArgumentException ex) {
                messageLabel.setText("Error: " + ex.getMessage());
            }
            
        });

        // Agregar componentes al panel
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Author:"));
        panel.add(authorField);
        panel.add(new JLabel("Genre:"));
        panel.add(genreField);
        panel.add(new JLabel("Year:"));
        panel.add(yearField);
        panel.add(saveButton);
        panel.add(messageLabel);

        return panel;
    }

    private JPanel createSearchBooksPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 10));

        // Campos de búsqueda
        JTextField searchField = new JTextField();
        JButton searchByTitleButton = new JButton("Search by Title");
        JButton searchByAuthorButton = new JButton("Search by Author");

        // Área de resultados
        JTextArea resultArea = new JTextArea();
        resultArea.setEditable(false);

        // Acciones de los botones
        searchByTitleButton.addActionListener(e -> {
            String title = searchField.getText();


            List<Book> books = bookService.searchBooksByTitle(title);
            displayBooks(resultArea, books);
        });

        searchByAuthorButton.addActionListener(e -> {
            String author = searchField.getText();
            List<Book> books = bookService.searchBooksByAuthor(author);
            displayBooks(resultArea, books);
        });

        // Panel de entrada y botones
        JPanel inputPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        inputPanel.add(searchField);
        inputPanel.add(searchByTitleButton);
        inputPanel.add(searchByAuthorButton);

        // Agregar componentes al panel principal
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        return panel;
    }

    private void displayBooks(JTextArea resultArea, List<Book> books) {
        resultArea.setText("");
        if (books.isEmpty()) {
            resultArea.append("No books found.\n");
        } else {
            for (Book book : books) {
                resultArea.append(book.toString() + "\n");
            }
        }
    }
}
