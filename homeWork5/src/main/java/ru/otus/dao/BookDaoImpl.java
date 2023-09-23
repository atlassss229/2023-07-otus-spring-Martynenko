package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.dao.mapper.BookMapper;
import ru.otus.model.Book;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookDaoImpl implements BookDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    private final BookMapper bookMapper = new BookMapper();

    @Override
    public void insertBook(Book book) {

        String sql = "INSERT INTO books (book_name, book_year, author_id, genre_id) " +
                "values (:name, :year, :authorId, :genreId)";

        jdbcOperations.update(sql, Map.of(
                "name", book.getName(),
                "year", book.getYear(),
                "authorId", book.getAuthor().getId(),
                "genreId", book.getGenre().getId()
        ));
    }

    @Override
    public List<Book> getAllBooks() {
        String sql =
                "SELECT " +
                "books.id, " +
                "book_name, " +
                "book_year, " +
                "books.author_id as author_id, " +
                "books.genre_id as genre_id, " +
                "authors.authors_name, " +
                "genres.genres_name " +
                "FROM books " +
                "LEFT JOIN authors ON books.author_id = authors.id " +
                "LEFT JOIN genres ON books.genre_id = genres.id";
        List<Book> allBooks = jdbcOperations.query(sql, bookMapper);
        return allBooks;
    }

    @Override
    public Book getBookById(Long id) {
        Map<String, Long> params = Map.of("id", id);
        try {
            String sql = String.format(
                    "SELECT " +
                    "%s as id, " +
                    "book_name, " +
                    "book_year, " +
                    "books.author_id as author_id, " +
                    "books.genre_id as genre_id, " +
                    "authors.authors_name, " +
                    "genres.genres_name " +
                    "FROM books " +
                    "LEFT JOIN authors ON books.author_id = authors.id " +
                    "LEFT JOIN genres ON books.genre_id = genres.id " +
                    "WHERE books.id = :id", id);

            return jdbcOperations.queryForObject(sql, params, bookMapper);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void deleteBookById(Long id) {
        Map<String, Long> params = Map.of("id", id);
        String sql =
                "DELETE " +
                "FROM books " +
                "WHERE " +
                "id = :id ";
        jdbcOperations.update(sql, params);
    }

    @Override
    public void updateBookById(Book book, Long id) {

        String sql =
                "UPDATE books " +
                "SET " +
                "book_name = :name, " +
                "book_year = :year, " +
                "author_id = :authorId, " +
                "genre_id = :genreId " +
                "WHERE id = :id";

        jdbcOperations.update(sql, Map.of(
                "id", id,
                "name", book.getName(),
                "year", book.getYear(),
                "authorId", book.getAuthor().getId(),
                "genreId", book.getGenre().getId()
        ));
    }
}
