package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.AuthorDao;
import ru.otus.model.Author;
import ru.otus.model.Genre;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    @Transactional
    public Optional<Author> findByName(String name) {
        return authorDao.findByName(name);
    }

    @Override
    @Transactional
    public Author save(Author author) {
        return authorDao.save(author);
    }

    @Override
    @Transactional
    public List<Author> getAllAuthors() {
        return authorDao.getAllAuthors();
    }


    @Override
    @Transactional
    public Author checkAuthor(Author author) {
        Optional<Author> authorDb = authorDao.findByName(author.getName());
        if (authorDb.isEmpty()) {
            author = authorDao.save(author);
        } else {
            author = authorDb.get();
        }
        return author;
    }
}
