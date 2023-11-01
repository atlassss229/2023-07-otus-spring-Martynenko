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
//@Import({BookRepository.class})
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private final Book testBook = new Book(
            "The Book",
            2222,
            new Author(null,"no"),
            new Genre(null,"no"));


    @Test
    public void getAllBooksTest() {
        List<Book> bookList = bookRepository.findAll();
        Assertions.assertEquals("Bad Book", bookList.get(1).getName());
    }

    @Test
    public void getBookByIdTest() {
        Book book = bookRepository.getBookById(2L);
        Assertions.assertEquals("Bad Book", book.getName());
    }

    @Test
    public void deleteBookByIdTest() {
        Book book = bookRepository.getBookById(1L);
        bookRepository.delete(book);
        book = bookRepository.getBookById(1L);
        Assertions.assertNull(book);
    }
}
