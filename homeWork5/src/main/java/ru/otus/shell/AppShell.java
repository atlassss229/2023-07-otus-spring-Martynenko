package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.model.Book;
import ru.otus.service.BookSrevice;
import ru.otus.service.IOService;
import ru.otus.service.LibraryRunService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class AppShell {

    private final LibraryRunService runService;

    private final IOService ioService;

    private final BookSrevice bookSrevice;

    @ShellMethod(value = "create-book", key = {"create-book", "cb"})
    public void createBook() {
        bookSrevice.createBook();
        ioService.printLine("book created");
    }

    @ShellMethod(value = "delete-book", key = {"delete-book", "db"})
    public void deleteBook() {
        ioService.printLine("book deleted");
    }

    @ShellMethod(value = "update-book", key = {"update-book", "ub"})
    public void updateBook() {
        ioService.printLine("book updated");
    }

    @ShellMethod(value = "get-book-by-id", key = {"get-book-by-id", "gb"})
    public void getBookById() {
        ioService.printLine("got book by id");
    }

    @ShellMethod(value = "get-all-books", key = {"get-all-books", "gab"})
    public void getAllBooks() {
        List<Book> bookList = bookSrevice.getAllBooks();
        for (var book : bookList) {
            ioService.printLine(book.getName());
        }
        ioService.printLine("got all books");
    }
}