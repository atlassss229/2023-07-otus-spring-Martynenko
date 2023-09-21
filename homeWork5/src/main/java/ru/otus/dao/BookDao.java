package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.dao.mapper.BookMapper;
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
                "SELECT " +
                "book_name, " +
                "book_year, " +
                "books.author_id as author_id, " +
                "books.genre_id as genre_id, " +
                "authors.authors_name, " +
                "genres.genres_name " +
                "FROM books " +
                "LEFT JOIN authors ON books.author_id = authors.id " +
                "LEFT JOIN genres ON books.genre_id = genres.id";
        List<Book> allBooks = jdbcTemplate.query(sql, bookMapper);
        return allBooks;
    }
}
