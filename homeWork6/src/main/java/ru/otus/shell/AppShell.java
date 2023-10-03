package ru.otus.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.dao.AuthorDaoImpl;
import ru.otus.dao.GenreDaoImpl;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Comment;
import ru.otus.model.Genre;
import ru.otus.service.BookSreviceImpl;
import ru.otus.service.CommentService;
import ru.otus.service.IOService;
import ru.otus.service.PrintBookService;

import java.util.List;
import java.util.regex.Pattern;

@ShellComponent
@RequiredArgsConstructor
public class AppShell {

    private final IOService ioService;

    private final BookSreviceImpl bookSreviceImpl;

    private final AuthorDaoImpl authorDaoImpl;

    private final GenreDaoImpl genreDaoImpl;

    private final PrintBookService printBookService;

    private final CommentService commentService;

    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    @ShellMethod(value = "create-book", key = {"create-book", "cb"})
    public void createBook() {
        Book book = new Book();
        getBookFromConsole(book);
        bookSreviceImpl.createBook(book);
        ioService.printLine("book created");
    }

    private void getBookFromConsole(Book book) {

        ioService.printLine("Enter book name:");
        String bookName = ioService.readLine();
        book.setName(bookName);

        ioService.printLine("Enter book year:");
        Integer bookYear = getNumeric();
        book.setYear(bookYear);

        ioService.printLine("Enter author name:");
        String authorName = ioService.readLine();
        book.setAuthor(new Author(authorName));

        ioService.printLine("Enter genre:");
        String genreName = ioService.readLine();
        book.setGenre(new Genre(genreName));
    }

    @ShellMethod(value = "delete-book", key = {"delete-book", "db"})
    public void deleteBook() {
        ioService.printLine("please enter book Id");
        Long id = Long.valueOf(getNumeric());
        bookSreviceImpl.deleteBook(id);
        ioService.printLine("book deleted");
    }

    @ShellMethod(value = "update-book", key = {"update-book", "ub"})
    public void updateBook() {
        ioService.printLine("please enter book Id");
        Long id = Long.valueOf(getNumeric());
        Book book = bookSreviceImpl.getBookById(id);
        if (book != null) {
            getBookFromConsole(book);
            bookSreviceImpl.updateBook(book);
        }
        ioService.printLine("book updated");
    }

    @ShellMethod(value = "get-book-by-id", key = {"get-book-by-id", "gb"})
    public void getBookById() {
        ioService.printLine("Enter book id:");
        Long id = Long.valueOf(getNumeric());
        Book book = bookSreviceImpl.getBookById(id);
        if (book != null) {
            printBookService.printBook(book);
        }
    }

    @ShellMethod(value = "print-all-books", key = {"print-all-books", "pab"})
    public void getAllBooks() {
        List<Book> bookList = bookSreviceImpl.getAllBooks();
        for (var book : bookList) {
            printBookService.printBook(book);
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

    @ShellMethod(value = "add-comment", key = {"add-comment", "ac"})
    public void addBooksComment() {
        ioService.printLine("please enter book Id");
        Long id = Long.valueOf(getNumeric());
        Book book = bookSreviceImpl.getBookById(id);
        ioService.printLine("Enter comment");
        String commentText = ioService.readLine();
        Comment comment = new Comment(commentText, book);
        commentService.saveComment(comment);
    }

    @ShellMethod(value = "get-comment-by-id", key = {"get-comment-by-id", "gc"})
    public void getCommentById() {
        ioService.printLine("please enter comment Id");
        Long id = Long.valueOf(getNumeric());
        Comment comment = commentService.getCommentById(id);
        if (comment != null) {
            ioService.printLine(comment.getText());
        }
    }

    @ShellMethod(value = "get-comments-by-book-id", key = {"get-comments-by-book-id", "gbc"})
    public void getCommentsByBookId() {
        ioService.printLine("please enter book Id");
        Long id = Long.valueOf(getNumeric());
        List<Comment> commentList = commentService.getCommentsByBookId(id);
        if (commentList != null) {
            for (Comment comment : commentList) {
                ioService.printLine(comment.getText());
            }
        }
    }

    @ShellMethod(value = "delete-comment-by-id", key = {"delete-comment-by-id", "dc"})
    public void deleteComment() {
        ioService.printLine("please enter comment Id");
        Long id = Long.valueOf(getNumeric());
        commentService.deleteCommentById(id);
    }

    @ShellMethod(value = "update-comment-by-id", key = {"update-comment-by-id", "uc"})
    public void updateComment() {
        ioService.printLine("please enter comment Id");
        Long id = Long.valueOf(getNumeric());
        Comment comment = commentService.getCommentById(id);
        if (comment != null) {
            ioService.printLine("please enter new comment");
            String newCommentText = ioService.readLine();
            comment.setText(newCommentText);
            commentService.updateCommentById(comment);
        }
    }
}