package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.model.Author;

import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author, Long> {
     Optional<Author> findByName(String name);

    List<Author> findAll();
}
