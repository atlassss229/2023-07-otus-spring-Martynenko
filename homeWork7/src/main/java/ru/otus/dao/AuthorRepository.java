package ru.otus.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.model.Author;

import java.util.List;


public interface AuthorRepository extends JpaRepository<Author, Long> {
     Author findByName(String name);

    boolean existsAuthorByName(String name);

    List<Author> findAll();
}
