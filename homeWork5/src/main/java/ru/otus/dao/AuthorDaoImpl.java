package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
            return Optional.empty();
        }
    }

    @Override
    public Author saveAuthor(String authorName) {
        KeyHolder holder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("authorName", authorName);
        String sql =
                "SELECT id FROM NEW TABLE (" +
                        "INSERT INTO authors (authors_name) " +
                        "VALUES (:authorName)" +
                        ")";

        jdbcOperations.update(sql, namedParameters, holder);
        return new Author(holder.getKey().longValue(), authorName);

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
