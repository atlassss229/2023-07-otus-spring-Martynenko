package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.dao.AuthorDaoImpl;
import ru.otus.dao.GenreDaoImpl;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AutorGenreServiceImpl implements AutorGenreService {

    private final AuthorDaoImpl authorDaoImpl;

    private final GenreDaoImpl genreDaoImpl;

    @Override
    public Book getAuthorAndGenreId(Book book) {

        String authorName = book.getAuthor().getName();
        Optional<Author> author = authorDaoImpl.findByName(authorName);
        if (author.isEmpty()) {
            author = Optional.ofNullable(authorDaoImpl.saveAuthor(authorName));
            book.setAuthor(author.get());
        } else {
            book.setAuthor(author.get());
        }

        String genreName = book.getGenre().getName();
        Optional<Genre> genre = genreDaoImpl.findByName(genreName);
        if (genre.isEmpty()) {
            genre = Optional.ofNullable(genreDaoImpl.save(genreName));
            book.setGenre(genre.get());
        } else {
            book.setGenre(genre.get());
        }

        return book;
    }
}
