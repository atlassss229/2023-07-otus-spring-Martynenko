package ru.otus.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.model.Genre;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Optional<Genre> findByName(String name) {

        TypedQuery<Genre> query = entityManager.createQuery(
                "SELECT g FROM Genre g WHERE g.name = :name", Genre.class);
        query.setParameter("name", name);
        return Optional.ofNullable(query.getResultList().isEmpty() ? null : query.getResultList().get(0));
    }

    @Override
    public Genre save(String genreName) {
        Genre author = new Genre(genreName);
        entityManager.persist(author);
        return findByName(genreName).get();
    }

    @Override
    public List<Genre> getAllGenres() {
        TypedQuery<Genre> query = entityManager.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }
}

