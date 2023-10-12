package ru.otus.service;

import ru.otus.model.Genre;

import java.util.List;

public interface GenreService {

    Genre save(Genre genre);


    List<Genre> getAllGenres();


    Genre checkGenre(Genre genre);
}
