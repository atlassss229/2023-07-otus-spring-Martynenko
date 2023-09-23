package ru.otus.dao;

import ru.otus.model.Book;

import java.util.List;

public interface BookDao {
    void insertBook(Book book);

    List<Book> getAllBooks();

    Book getBookById(Long id);

    void deleteBookById(Long id);

    void updateBookById(Book book, Long id);
}
