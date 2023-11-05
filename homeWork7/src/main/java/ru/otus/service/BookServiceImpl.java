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
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Override
    @Transactional
    public Book createBook(Book book) {
        loadAuthorAndGenre(book);
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
        return bookRepository.findById(id).orElseThrow(() -> new NotFoundException("book not found"));
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateBookById(Book book) {
        bookRepository.findById(book.getId()).orElseThrow(() -> new NotFoundException("book not found"));
        loadAuthorAndGenre(book);
        bookRepository.save(book);
    }

    private void loadAuthorAndGenre(Book book) {
        String authorName = book.getAuthor().getName();
        Optional<Author> authorFromDb = authorRepository.findByName(authorName);
        if (authorFromDb.isEmpty()) {
            authorFromDb = Optional.of(authorRepository.save(book.getAuthor()));
        }
        book.setAuthor(authorFromDb.get());

        String genreName = book.getGenre().getName();
        Optional<Genre> genreFromDb = genreRepository.findByName(genreName);
        if (genreFromDb.isEmpty()) {
            genreFromDb = Optional.of(genreRepository.save(book.getGenre()));
        }
        book.setGenre(genreFromDb.get());
    }
}

