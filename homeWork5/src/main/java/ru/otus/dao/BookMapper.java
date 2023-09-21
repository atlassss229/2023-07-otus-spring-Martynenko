package ru.otus.dao;

import org.springframework.jdbc.core.RowMapper;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet resultSet, int rowNum) {
        try {
            return new Book(
                    resultSet.getString("book_name"),
                    resultSet.getInt("book_year"),
                    new Author(resultSet.getLong("author_id"),
                            null),
                    new Genre(resultSet.getLong("genre_id"),
                            null));
        } catch (SQLException e) {
            System.out.println("sql ooops");
            return null;
        }
    }
}