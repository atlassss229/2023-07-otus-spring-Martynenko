package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorRepository;
import ru.otus.dao.BookRepository;
import ru.otus.dao.GenreRepository;
import ru.otus.exception.NotFoundException;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public Book createBook(Book book) {
        checkAuthorAndGenre(book);
        return bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long id) {
        Book book = bookRepository.getBookById(id);
        if (book == null) {
            throw new NotFoundException("book not found");
        }
        return book;
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    @Override
    @Transactional
    public void updateBookById(Book book) {
        getBookById(book.getId());
        checkAuthorAndGenre(book);
        bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    protected void checkAuthorAndGenre(Book book) {
        String authorName = book.getAuthor().getName();
        Author author = authorRepository.findByName(authorName);
        if (author != null) {
            book.setAuthor(author);
        }

        String genreName = book.getGenre().getName();
        Genre genre = genreRepository.findByName(genreName);
        if (genre != null) {
            book.setGenre(genre);
        }
    }
}

