package ru.otus.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {
    private long id;

    private final String name;

    public Author(String name) {
        this.name = name;
    }

    public Author(long id, String name) {
        this.id = id;
        this.name = name;
    }
}