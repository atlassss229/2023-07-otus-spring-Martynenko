package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.mapper.GenreMapper;
import ru.otus.model.Genre;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    private final GenreMapper genreMapper = new GenreMapper();

    @Override
    public Genre getIdByNAme(String name) {
        Map<String, String> params = Map.of("name", name);
        try {
            String sql = "" +
                    "SELECT " +
                    "id, " +
                    "genres_name " +
                    "FROM genres " +
                    "WHERE genres_name = :name";
            return jdbcOperations.queryForObject(sql, params, genreMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void insertNewGenre(String genreName) {
        Map<String, String> params = Map.of("genreName", genreName);
        String sql = "" +
                "INSERT INTO genres (genres_name) " +
                "VALUES (:genreName) ";
        jdbcOperations.update(sql, params);
    }

    @Override
    public List<Genre> getAllGenres() {
        String sql = "" +
                "SELECT  " +
                "id, " +
                "genres_name " +
                "FROM genres";
        return jdbcOperations.query(sql, genreMapper);
    }
}

