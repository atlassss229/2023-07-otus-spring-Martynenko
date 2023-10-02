package ru.otus.dao;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.model.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void save(Book book) {
        if (book.getId() == null) {
            entityManager.persist(book);
        } else {
            entityManager.merge(book);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        EntityGraph<?> entityGraph = entityManager.getEntityGraph("otus-book-author-genre-entity-graph");
        TypedQuery<Book> query = entityManager.createQuery("select b from Book b", Book.class);
        query.setHint(FETCH.getKey(), entityGraph);
        return query.getResultList();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.fetchgraph",
                entityManager.getEntityGraph("otus-book-author-genre-entity-graph"));
        return Optional.ofNullable(entityManager.find(Book.class, id, properties));
    }

    @Override
    public void deleteBookById(Long id) {
        Optional<Book> book = getBookById(id);
        book.ifPresent(entityManager::remove);
    }
}
