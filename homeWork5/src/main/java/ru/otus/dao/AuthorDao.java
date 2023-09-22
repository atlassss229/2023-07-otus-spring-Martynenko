package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.otus.dao.mapper.AuthorMapper;


@Repository
@RequiredArgsConstructor
public class AuthorDao {

    private final JdbcTemplate jdbcTemplate;

    private final AuthorMapper authorMapper = new AuthorMapper();

    getIdByNAme

    insertNewAuthor

}
