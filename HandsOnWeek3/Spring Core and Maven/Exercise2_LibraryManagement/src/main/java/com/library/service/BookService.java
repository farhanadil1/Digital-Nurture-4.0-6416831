package com.library.service;

import com.library.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    // Setter for Dependency Injection
    public void setBookRepository(BookRepository bookRepository) {
        System.out.println("Setter injection: BookRepository injected into BookService");
        this.bookRepository = bookRepository;
    }

    public void printBookTitle() {
        String title = bookRepository.getBookTitle();
        System.out.println("Book Title: " + title);
    }
}
