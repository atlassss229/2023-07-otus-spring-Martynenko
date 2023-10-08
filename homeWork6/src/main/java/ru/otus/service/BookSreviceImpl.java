package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDaoImpl;
import ru.otus.dao.GenreDao;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookSreviceImpl implements BookSrevice {

    private final BookDaoImpl bookDaoImpl;

    private final GenreDao genreDao;

    private final AuthorDao authorDao;


    @Override
    @Transactional
    public Book createBook(Book book) {
        Author author = book.getAuthor();
        book.setAuthor(authorDao.save(author));
        Genre genre = book.getGenre();
        book.setGenre(genreDao.save(genre));
        return bookDaoImpl.save(book);
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
            return book.orElse(null);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookDaoImpl.deleteBookById(id);
    }
}

