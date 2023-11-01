package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.model.Genre;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findByName(String name);

    boolean existsGenreByName(String name);

    List<Genre> findAll();
}
