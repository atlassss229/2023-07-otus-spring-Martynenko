package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorDaoImpl;
import ru.otus.dao.BookDaoImpl;
import ru.otus.dao.GenreDaoImpl;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookSreviceImpl implements BookSrevice {

    private final BookDaoImpl bookDaoImpl;

    private final IOService ioService;

    private final AuthorDaoImpl authorDaoImpl;

    private final GenreDaoImpl genreDaoImpl;

    @Override
    @Transactional
    public void createBook(Book book) {
        book = getAuthorAndGenreId(book);
        bookDaoImpl.save(book);
    }

    @Override
    public Book getAuthorAndGenreId(Book book) {

        String authorName = book.getAuthor().getName();
        Optional<Author> author = authorDaoImpl.findByName(authorName);
        if (author.isEmpty()) {
            author = Optional.ofNullable(authorDaoImpl.saveAuthor(authorName));
            book.setAuthor(author.get());
        } else {
            book.setAuthor(author.get());
        }

        String genreName = book.getGenre().getName();
        Optional<Genre> genre = genreDaoImpl.findByName(genreName);
        if (genre.isEmpty()) {
            genre = Optional.ofNullable(genreDaoImpl.save(genreName));
            book.setGenre(genre.get());
        } else {
            book.setGenre(genre.get());
        }

        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookDaoImpl.getAllBooks();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        Optional<Book> book = bookDaoImpl.getBookById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            ioService.printLine("no book with such id...");
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if (getBookById(id) != null) {
            bookDaoImpl.deleteBookById(id);
        }
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        book = getAuthorAndGenreId(book);
        bookDaoImpl.save(book);
    }
}

