package ru.otus.dao;

import ru.otus.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    void deleteBookById(Long id);

    void updateBook(Book book);
}
