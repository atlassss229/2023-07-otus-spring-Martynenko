package ru.otus.service;

import ru.otus.model.Book;

import java.util.List;

public interface BookSrevice {
    void createBook();

    Book getBookInfo();

    List<Book> getAllBooks();

    Book getBookById();

    Integer getNumeric();

    void printBook(Book book);

    void deleteBook();

    void updateBook();
}
