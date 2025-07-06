Exercise 2 – Implementing Dependency Injection

This project demonstrates setter based Dependency Injection using Spring XML configuration.

BookRepository is injected into BookService using a setter method.
The DI is defined in applicationContext.xml via the <property> tag.
The setter prints a message when it's called to confirm DI is working.
