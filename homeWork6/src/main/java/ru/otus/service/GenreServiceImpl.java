package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dao.GenreDao;
import ru.otus.model.Genre;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    @Transactional
    public Optional<Genre> findByName(String name) {
        return genreDao.findByName(name);
    }

    @Override
    @Transactional
    public Genre save(Genre genre) {
        return genreDao.save(genre);
    }

    @Override
    @Transactional
    public List<Genre> getAllGenres() {
        return genreDao.getAllGenres();
    }

    @Override
    @Transactional
    public Genre checkGenre(Genre genre) {
        Optional<Genre> genreDb = genreDao.findByName(genre.getName());
        if (genreDb.isEmpty()) {
            genre = genreDao.save(genre);
        } else {
            genre = genreDb.get();
        }
        return genre;
    }

}
