package ru.otus.dao;

import ru.otus.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
     Optional<Author> findByName(String name);

    Author saveAuthor(String authorName);

    List<Author> getAllAuthors();
}
