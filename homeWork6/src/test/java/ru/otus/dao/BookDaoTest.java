package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@Import({BookDaoImpl.class})
public class BookDaoTest {

    @Autowired
    private BookDaoImpl bookDao;

    private final Book testBook = new Book(
            "The Book",
            2222,
            new Author(null,"no"),
            new Genre(null,"no"));

    @Test
    public void getAllBooksTest() {
        List<Book> bookList = bookDao.getAllBooks();
        Assertions.assertEquals("Bad Book", bookList.get(1).getName());
    }

    @Test
    public void getBookByIdTest() {
        Book book = bookDao.getBookById(2L).get();
        Assertions.assertEquals("Bad Book", book.getName());
    }
    @Test
    public void insertBookTest() {
        Assertions.assertEquals("The Book", bookDao.save(testBook).getName());
        Book book = bookDao.getBookById(3L).get();
        Assertions.assertEquals("The Book", book.getName());
    }

    @Test
    public void deleteBookByIdTest() {
        testBook.setId(1L);
        bookDao.deleteBookById(1L);
        Optional<Book> book = bookDao.getBookById(1L);
        Assertions.assertTrue(book.isEmpty());
    }
}
