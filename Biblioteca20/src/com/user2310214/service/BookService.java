/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.user2310214.service;

import com.user2310214.repository.BookRepository;
import com.user2310214.model.Book;
import java.util.List;
/**
 *
 * @author tracy
 */
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String title, String author, String genre, int year) {
        Book book = new Book.BookBuilder()
                .setTitle(title)
                .setAuthor(author)
                .setGenre(genre)
                .setYear(year)
                .build();
        bookRepository.save(book);
    }

    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
        
        
    public List<Book> searchBooksByTitle(String title ) {
        return bookRepository.findByTitle(title);
    }
    public List<Book> searchBooksByAuthor(String autor) {
        return bookRepository.findByAuthor(autor);
    }

    public void updateBook(int id, String title, String author, String genre, int year) {
        Book updatedBook = new Book.BookBuilder()
              //  .setId(id)
                .setTitle(title)
                .setAuthor(author)
                .setGenre(genre)
                .setYear(year)
                .build();
        bookRepository.update(updatedBook);
    }

    public void deleteBook(int id) {
        bookRepository.delete(id);
    }
}

 