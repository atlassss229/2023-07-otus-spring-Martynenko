package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.dao.mapper.GenreMapper;
import ru.otus.model.Genre;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreDaoImpl implements GenreDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    private final GenreMapper genreMapper = new GenreMapper();

    @Override
    public Optional<Genre> findByName(String name) {
        Map<String, String> params = Map.of("name", name);
        try {
            String sql =
                    "SELECT " +
                            "id, " +
                            "genres_name " +
                            "FROM genres " +
                            "WHERE genres_name = :name";
            return Optional.ofNullable(jdbcOperations.queryForObject(sql, params, genreMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Genre save(String genreName) {
        KeyHolder holder = new GeneratedKeyHolder();
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("genreName", genreName);
        String sql =
                "SELECT id FROM NEW TABLE (" +
                        "INSERT INTO genres (genres_name) " +
                        "VALUES (:genreName)" +
                        ")";

        jdbcOperations.update(sql, namedParameters, holder);

        return new Genre(holder.getKey().longValue(), genreName);

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

