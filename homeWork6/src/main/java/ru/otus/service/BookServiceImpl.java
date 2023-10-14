package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.exception.NotFoundException;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    @Override
    @Transactional
    public Book createBook(Book book) {
        checkAuthorAndGenre(book);
        return bookDao.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookDao.getAllBooks();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        return bookDao.getBookById(id).orElseThrow(() -> new NotFoundException("book not found"));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookDao.deleteBook(book);
    }

    @Override
    @Transactional
    public void updateBookById(Book book) {
        getBookById(book.getId());
        checkAuthorAndGenre(book);
        bookDao.save(book);
    }

    private void checkAuthorAndGenre(Book book) {
        String authorName = book.getAuthor().getName();
        Optional<Author> author = authorDao.findByName(authorName);
        author.ifPresent(book::setAuthor);

        String genreName = book.getGenre().getName();
        Optional<Genre> genre = genreDao.findByName(genreName);
        genre.ifPresent(book::setGenre);
    }
}

