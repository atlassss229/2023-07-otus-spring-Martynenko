package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.mapper.AuthorMapper;
import ru.otus.model.Author;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    private final AuthorMapper authorMapper = new AuthorMapper();

    @Override
    public Optional<Author> findByName(String name) {
        try {
            Map<String, String> params = Map.of("name", name);
            String sql = "" +
                    "SELECT " +
                    "id, " +
                    "authors_name " +
                    "FROM authors " +
                    "WHERE authors_name = :name";
            return Optional.ofNullable(jdbcOperations.queryForObject(sql, params, authorMapper));
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Author saveAuthor(String authorName) {
        Map<String, String> params = Map.of("authorName", authorName);
        String sql =
                "SELECT id FROM NEW TABLE (" +
                        "INSERT INTO authors (authors_name) " +
                        "VALUES (:authorName)" +
                        ")";
        try {
            Long id = jdbcOperations.queryForObject(sql, params, Long.class);
            return new Author(id, authorName);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        String sql = "" +
                "SELECT  " +
                "id, " +
                "authors_name " +
                "FROM authors";
        return jdbcOperations.query(sql, authorMapper);
    }
}
