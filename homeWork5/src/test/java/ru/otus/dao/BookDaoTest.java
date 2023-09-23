package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;

import java.util.List;

@JdbcTest
@Import(BookDaoImpl.class)
public class BookDaoTest {

    @Autowired
    private BookDaoImpl bookDao;

    private final Book testBook = new Book(
            "The Book",
            2222,
            new Author(1,"no"),
            new Genre(1,"no"));

    @Test
    public void getAllBooksTest() {
        List<Book> bookList = bookDao.getAllBooks();
        Assertions.assertEquals("Bad Book", bookList.get(1).getName());
    }

    @Test
    public void getBookByIdTest() {
        Book book = bookDao.getBookById(2L);
        Assertions.assertEquals("Bad Book", book.getName());
    }
    @Test
    public void insertBookTest() {
        bookDao.insertBook(testBook);
        Book book = bookDao.getBookById(3L);
        Assertions.assertEquals("The Book", book.getName());
    }

    @Test
    public void deleteBookByIdTest() {
        bookDao.deleteBookById(1L);
        Book book = bookDao.getBookById(1L);
        Assertions.assertNull(book);
    }

    @Test
    public void updateBookByIdTest() {
        bookDao.updateBookById(testBook, 1L);
        Book book = bookDao.getBookById(1L);
        Assertions.assertEquals("The Book", book.getName());
    }

}
