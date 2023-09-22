package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;

import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class BookSrevice {

    private final BookDao bookDao;

    private final IOService ioService;

    private final AuthorDao authorDao;

    private final GenreDao genreDao;

    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public void createBook() {
        ioService.printLine("Enter book name:");
        String bookName = ioService.readLine();

        ioService.printLine("Enter book year:");
        Integer bookYear = getNumeric(ioService.readLine());

        ioService.printLine("Enter author name:");
        String authorName = ioService.readLine();
        Author author = authorDao.getIdByNAme(authorName);
        if (author == null) {
            genreDao.insertNewAuthor(authorName);
        }

        ioService.printLine("Enter genre:");
        String genreName = ioService.readLine();
        Genre genre = genreDao.getIdByNAme(genreName);
        if (genre == null) {
            genreDao.insertNewGenre(genreName);
        }

        Book book = new Book(bookName, bookYear, author, genre);
        bookDao.insertBook(book);
    }

    public List<Book> getAllBooks() {
        return bookDao.getAallBooks();
    }

    public Integer getNumeric (String strNum) {
        while (true) {
            if (strNum != null && pattern.matcher(strNum).matches()) {
                return Integer.parseInt(strNum);
            } else {
                ioService.printLine("please enter numeric value");
            }
        }
    }
}
