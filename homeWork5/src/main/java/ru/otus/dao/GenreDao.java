package ru.otus.dao;

import ru.otus.model.Genre;

import java.util.List;

public interface GenreDao {
    Genre getIdByNAme(String name);

    void insertNewGenre(String genreName);

    List<Genre> getAllGenres();
}
