package ru.otus.service;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.model.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    @Transactional
    Optional<Author> findByName(String name);

    @Transactional
    Author save(Author author);

    @Transactional
    List<Author> getAllAuthors();

    @Transactional
    Author checkAuthor(Author author);
}
