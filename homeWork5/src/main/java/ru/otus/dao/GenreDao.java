package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.dao.mapper.AuthorMapper;
import ru.otus.dao.mapper.GenreMapper;
import ru.otus.model.Genre;

@Repository
@RequiredArgsConstructor
public class GenreDao {

    private final JdbcTemplate jdbcTemplate;

    private final GenreMapper genreMapper = new GenreMapper();

    public Genre getIdByNAme(String name) {
        try {
            return jdbcTemplate.queryForObject("" +
                    "SELECT " +
                    "genre_id, " +
                    "genre_name" +
                    "FROM genres", genreMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void insertNewGenre() {

    }
}

