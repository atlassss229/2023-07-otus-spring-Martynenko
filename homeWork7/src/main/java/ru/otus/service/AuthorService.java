package ru.otus.service;

import ru.otus.model.Author;

import java.util.List;

public interface AuthorService {

    Author save(Author author);

    List<Author> getAllAuthors();
}
