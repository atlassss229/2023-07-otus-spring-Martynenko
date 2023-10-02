package ru.otus.dao;

import ru.otus.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    Optional<Genre> findByName(String name);

    Genre save(String genreName);

    List<Genre> getAllGenres();
}
