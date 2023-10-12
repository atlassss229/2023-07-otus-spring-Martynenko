package ru.otus.service;

import ru.otus.model.Book;

import java.util.List;

public interface BookService {
    Book createBook(Book book);

    List<Book> getAllBooks();

    Book getBookById(Long id);

    void deleteBook(Long id);

    void updateBookId(Book book);

}
