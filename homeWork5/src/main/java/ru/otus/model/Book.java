package ru.otus.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private long id;

    private final String name;

    private final Integer year;

    private final Author author;

    private final Genre genre;

    public Book(String name, Integer year, Author author, Genre genre) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }
}

