package ru.otus.dao;

import ru.otus.model.Author;

import java.util.List;

public interface AuthorDao {
    Author getIdByNAme(String name);

    void insertNewAuthor(String authorName);

    List<Author> getAllAuthors();
}
