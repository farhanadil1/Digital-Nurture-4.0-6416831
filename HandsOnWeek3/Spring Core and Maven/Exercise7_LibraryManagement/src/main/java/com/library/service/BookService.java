package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;
    private String libraryName;

    // Constructor for library name injection
    public BookService(String libraryName) {
        this.libraryName = libraryName;
    }

    // Setter for repository
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void printBookTitle() {
        System.out.println("Library: " + libraryName);
        System.out.println("Book Title: " + bookRepository.getBookTitle());
    }
}
