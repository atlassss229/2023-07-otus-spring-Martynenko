package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorDaoImpl;
import ru.otus.dao.BookDaoImpl;
import ru.otus.dao.GenreDaoImpl;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class BookSreviceImpl implements BookSrevice {

    private final BookDaoImpl bookDaoImpl;

    private final IOService ioService;

    private final AuthorDaoImpl authorDaoImpl;

    private final GenreDaoImpl genreDaoImpl;

    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @Override
    public void createBook() {
        Book book = getBookInfo();
        bookDaoImpl.insertBook(book);
    }

    @Override
    public Book getBookInfo() {
        ioService.printLine("Enter book name:");
        String bookName = ioService.readLine();

        ioService.printLine("Enter book year:");
        Integer bookYear = getNumeric();

        ioService.printLine("Enter author name:");
        String authorName = ioService.readLine();
        Author author = authorDaoImpl.getIdByNAme(authorName);
        if (author == null) {
            authorDaoImpl.insertNewAuthor(authorName);
            author = authorDaoImpl.getIdByNAme(authorName);
        }
        ioService.printLine("Enter genre:");
        String genreName = ioService.readLine();
        Genre genre = genreDaoImpl.getIdByNAme(genreName);
        if (genre == null) {
            genreDaoImpl.insertNewGenre(genreName);
            genre = genreDaoImpl.getIdByNAme(genreName);
        }
        return new Book(bookName, bookYear, author, genre);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDaoImpl.getAllBooks();
    }

    @Override
    public Book getBookById() {
        ioService.printLine("Enter book id:");
        Long id = Long.valueOf(getNumeric());
        return bookDaoImpl.getBookById(id);
    }

    @Override
    public Integer getNumeric() {
        while (true) {
            String strNum = ioService.readLine();
            if (strNum != null && pattern.matcher(strNum).matches()) {
                return Integer.parseInt(strNum);
            } else {
                ioService.printLine("please enter numeric value");
            }
        }
    }

    @Override
    public void printBook(Book book) {
        ioService.printLine("\n----------");
        ioService.printLine(String.valueOf(book.getId()));
        ioService.printLine(book.getName());
        ioService.printLine(book.getYear().toString());
        ioService.printLine(book.getAuthor().getName());
        ioService.printLine(book.getGenre().getName());
        ioService.printLine("----------");
    }

    @Override
    public void deleteBook() {
        ioService.printLine("please enter book Id");
        Long id = Long.valueOf(getNumeric());
        if (bookDaoImpl.getBookById(id) != null) {
            bookDaoImpl.deleteBookById(id);
        } else {
            ioService.printLine("no book with such id...");
        }
    }

    @Override
    public void updateBook() {
        ioService.printLine("please enter book Id");
        Long id = Long.valueOf(getNumeric());
        Book oldBook = bookDaoImpl.getBookById(id);
        if (oldBook != null) {
            Book newBook = getBookInfo();
            bookDaoImpl.updateBookById(newBook, id);

        } else {
            ioService.printLine("no book with such id...");
        }
    }
}
