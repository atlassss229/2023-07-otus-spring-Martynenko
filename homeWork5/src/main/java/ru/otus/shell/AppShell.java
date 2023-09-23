package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.dao.AuthorDaoImpl;
import ru.otus.dao.GenreDaoImpl;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;
import ru.otus.service.BookSreviceImpl;
import ru.otus.service.IOService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AppShell {

    private final IOService ioService;

    private final BookSreviceImpl bookSreviceImpl;

    private final AuthorDaoImpl authorDaoImpl;

    private final GenreDaoImpl genreDaoImpl;

    @ShellMethod(value = "create-book", key = {"create-book", "cb"})
    public void createBook() {
        bookSreviceImpl.createBook();
        ioService.printLine("book created");
    }

    @ShellMethod(value = "delete-book", key = {"delete-book", "db"})
    public void deleteBook() {
        bookSreviceImpl.deleteBook();
        ioService.printLine("book deleted");
    }

    @ShellMethod(value = "update-book", key = {"update-book", "ub"})
    public void updateBook() {
        bookSreviceImpl.updateBook();
        ioService.printLine("book updated");
    }

    @ShellMethod(value = "get-book-by-id", key = {"get-book-by-id", "gb"})
    public void getBookById() {
        Book book = bookSreviceImpl.getBookById();
        if (book == null) {
            ioService.printLine("no book with such id...");
        } else {
            bookSreviceImpl.printBook(book);
        }
    }

    @ShellMethod(value = "print-all-books", key = {"print-all-books", "pab"})
    public void getAllBooks() {
        List<Book> bookList = bookSreviceImpl.getAllBooks();
        for (var book : bookList) {
            bookSreviceImpl.printBook(book);
        }
    }

    @ShellMethod(value = "print-all-genre", key = {"print-all-genre", "pag"})
    public void printAllGenres() {
        List<Genre> genreList = genreDaoImpl.getAllGenres();
        ioService.printLine("");
        for (var genre : genreList) {
            ioService.printLine(genre.getName());
        }
    }

    @ShellMethod(value = "print-all-authors", key = {"print-all-authors", "paa"})
    public void printAllAuthors() {
        List<Author> authorList = authorDaoImpl.getAllAuthors();
        ioService.printLine("");
        for (var author : authorList) {
            ioService.printLine(author.getName());
        }
    }
}