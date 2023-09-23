package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.mapper.AuthorMapper;
import ru.otus.model.Author;

import java.util.List;
import java.util.Map;


@Repository
@RequiredArgsConstructor
public class AuthorDaoImpl implements AuthorDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    private final AuthorMapper authorMapper = new AuthorMapper();

    @Override
    public Author getIdByNAme(String name) {
        try {
            Map<String, String> params = Map.of("name", name);
            String sql = "" +
                    "SELECT " +
                    "id, " +
                    "authors_name " +
                    "FROM authors " +
                    "WHERE authors_name = :name";
            return jdbcOperations.queryForObject(sql, params, authorMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void insertNewAuthor(String authorName) {
        Map<String, String> params = Map.of("authorName", authorName);
        String sql = "" +
                "INSERT INTO authors (authors_name) " +
                "VALUES (:authorName)";
        jdbcOperations.update(sql, params);
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
