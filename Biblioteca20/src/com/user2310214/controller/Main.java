/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.user2310214.controller;
import com.user2310214.model.Book; 
import com.user2310214.repository.BookRepository; 
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;


/**
 *
 * @author tracy
 */
public class Main {
 public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String password = "Tracy997570268";

        BookRepository repository = new BookRepository(url, user, password);

        // Crear y guardar libros
        Book book1 = new Book.BookBuilder()
                .setTitle("1984")
                .setAuthor("George Orwell")
                .setGenre("Dystopian")
                .setYear(1949)
                .build();

        Book book2 = new Book.BookBuilder()
                .setTitle("To Kill a Mockingbird")
                .setAuthor("Harper Lee")
                .setGenre("Fiction")
                .setYear(1960)
                .build();

//        repository.save(book1);
//        repository.save(book2);
        
        // Eliminar el registro 
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Ingresa el ID del libro a eliminar: ");
            int id = scanner.nextInt();  
            repository.delete(id);
            System.out.println("Libro Eliminado...");
            } catch (Exception e) {
                System.out.print("Error al eliminar el Libro.");
            }


        // Consultar libros
        System.out.println("Books by author 'George Orwell':");
        List<Book> booksByAuthor = repository.findByAuthor("George Orwell");
        booksByAuthor.forEach(System.out::println);

        System.out.println("Books with title containing '1984':");
        List<Book> booksByTitle = repository.findByTitle("1984");
        booksByTitle.forEach(System.out::println);
    }
}