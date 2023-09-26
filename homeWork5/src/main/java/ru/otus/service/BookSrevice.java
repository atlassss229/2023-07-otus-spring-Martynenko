package ru.otus.service;

import ru.otus.model.Book;

import java.util.List;

public interface BookSrevice {
    void createBook(Book book);

    Book getAuthorAndGenreId(Book book);

    List<Book> getAllBooks();

    Book getBookById(Long id);

    void deleteBook(Long id);

    void updateBook(Book book);
}
