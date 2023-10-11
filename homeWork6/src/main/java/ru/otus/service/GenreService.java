package ru.otus.service;

import ru.otus.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    Optional<Genre> findByName(String name);


    Genre save(Genre genre);


    List<Genre> getAllGenres();


    Genre checkGenre(Genre genre);
}
