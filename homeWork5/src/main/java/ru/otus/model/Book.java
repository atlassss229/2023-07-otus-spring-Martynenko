package ru.otus.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Book {
    private long id;

    private String name;

    private Integer year;

    private Author author;

    private Genre genre;

    public Book(Long id, String name, Integer year, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }

    public Book(String name, Integer year, Author author, Genre genre) {
        this.name = name;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }
}

