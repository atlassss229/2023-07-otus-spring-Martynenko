package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;

import java.util.List;

@DataJpaTest
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
        Book book = bookRepository.findById(2L).get();
        Assertions.assertEquals("Bad Book", book.getName());
    }

    @Test
    public void deleteBookByIdTest() {
        Book book = bookRepository.findById(1L).get();
        bookRepository.delete(book);
        Assertions.assertTrue(bookRepository.findById(1L).isEmpty());
    }
}
