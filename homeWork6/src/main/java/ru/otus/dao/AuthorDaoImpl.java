package ru.otus.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.model.Author;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Optional<Author> findByName(String name) {
        TypedQuery<Author> query = entityManager.createQuery(
                "SELECT a FROM Author a WHERE a.name = :name", Author.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getResultList().isEmpty() ? null : query.getResultList().get(0));
    }

    @Override
    public Author saveAuthor(String authorName) {
        Author author = new Author(authorName);
        entityManager.persist(author);
        return author;
        }

    @Override
    public List<Author> getAllAuthors() {
        TypedQuery<Author> query = entityManager.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }
}
