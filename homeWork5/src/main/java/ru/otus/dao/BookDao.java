package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.model.Book;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    private final BookMapper bookMapper = new BookMapper();

    public void insertBook(Book book) {
        String sql = String.format("" +
                        "INSERT INTO books (book_name, book_year, author_id, genre_id) " +
                        "values ('%s', %d, %d, %d)",
                        book.getName(),
                        book.getYear(),
                        book.getAuthor().getId(),
                        book.getGenre().getId());

        jdbcTemplate.update(sql);
    }

    public List<Book> getAallBooks() {
        String sql = "" +
                "SELECT * " +
                "FROM books";
        List<Book> allBooks = jdbcTemplate.query(sql, bookMapper);
        return allBooks;
    }
}
