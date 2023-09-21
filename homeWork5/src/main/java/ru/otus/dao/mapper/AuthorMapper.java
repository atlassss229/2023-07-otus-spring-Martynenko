package ru.otus.dao.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.model.Author;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet resultSet, int rowNum) {
        try {
            return new Author(
                    resultSet.getLong("id"),
                    resultSet.getString("authors_name"));
        } catch (SQLException e) {
            System.out.println("AuthorMapperError");
            return null;
        }
    }
}
