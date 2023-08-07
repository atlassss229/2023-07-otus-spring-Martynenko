package ru.otus.model;

public class Answer {
    private final String value;

    private final boolean isCoorect;

    public boolean isCoorect() {
        return isCoorect;
    }

    public Answer(String value, boolean isCoorect) {
        this.value = value;
        this.isCoorect = isCoorect;
    }

    public String getValue() {
        return value;
    }
}