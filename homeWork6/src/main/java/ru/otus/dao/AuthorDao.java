package ru.otus.dao;

import ru.otus.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
     Optional<Author> findByName(String name);

    Author save(Author author);

    List<Author> getAllAuthors();
}
