package ru.otus.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {
    private Long id;

    private final String name;

    public Author(String name) {
        this.name = name;
    }

    public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}