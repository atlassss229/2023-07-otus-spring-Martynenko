package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookDaoImpl;
import ru.otus.model.Book;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookSreviceImpl implements BookSrevice {

    private final BookDaoImpl bookDaoImpl;

    private final IOService ioService;

    private final AutorGenreService autorGenreService;


    @Override
    @Transactional
    public void createBook(Book book) {
        book = autorGenreService.getAuthorAndGenreId(book);
        bookDaoImpl.save(book);
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
        return book.orElse(null);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        if (bookDaoImpl.getBookById(id).isEmpty()) {
            bookDaoImpl.deleteBookById(id);
        }
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        book = autorGenreService.getAuthorAndGenreId(book);
        bookDaoImpl.save(book);
    }


}

