package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.BookDao;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookSrevice {

    private final BookDao bookDao;

    public void createBook() {
        Book book = new Book(
                "Book1",
                2001,
                new Author(1, "Grosssssss"),
                new Genre(1, "FuckShit")
        );
        bookDao.insertBook(book);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAallBooks();
    }
}
