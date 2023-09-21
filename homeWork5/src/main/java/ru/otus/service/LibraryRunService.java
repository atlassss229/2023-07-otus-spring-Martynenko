package ru.otus.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@RequiredArgsConstructor
public class LibraryRunService {

    private final JdbcTemplate jdbcTemplate;

    private final IOService ioService;

    public void run() {
        ioService.printLine("T!!!!!");
        String sql = ("" +
                "SELECT authors_name " +
                "FROM authors");
        String result = jdbcTemplate.queryForObject(sql, String.class);
        ioService.printLine(result);
    }
}
