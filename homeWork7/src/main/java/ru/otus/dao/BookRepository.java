package ru.otus.dao;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    @EntityGraph(value = "otus-book-author-genre-entity-graph")
    List<Book> findAll();

    @EntityGraph(value = "otus-book-author-genre-entity-graph")
    Optional<Book> findById(Long id);
}
