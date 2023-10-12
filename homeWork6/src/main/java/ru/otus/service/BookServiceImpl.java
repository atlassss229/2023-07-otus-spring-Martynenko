package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.BookDao;
import ru.otus.exception.NotFoundException;
import ru.otus.model.Book;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    @Override
    @Transactional
    public Book createBook(Book book) {
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
        bookDao.deleteBookById(id);
    }

    @Override
    @Transactional
    public void updateBookId(Book book) {
        getBookById(book.getId());
        bookDao.save(book);
    }
}

