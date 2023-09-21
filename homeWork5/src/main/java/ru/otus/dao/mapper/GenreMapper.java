package ru.otus.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GenreMapper implements RowMapper<Genre> {
    @Override
    public Genre mapRow(ResultSet resultSet, int rowNum) {
        try {
            return new Genre(
                    resultSet.getLong("id"),
                    resultSet.getString("genres_name"));
        } catch (SQLException e) {
            System.out.println("AuthorMapperError");
            return null;
        }
    }
}
