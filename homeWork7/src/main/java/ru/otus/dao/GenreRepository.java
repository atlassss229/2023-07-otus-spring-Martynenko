package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.model.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);

    List<Genre> findAll();
}
