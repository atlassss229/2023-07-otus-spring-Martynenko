package ru.otus.model;

public class Answer {
    private final String value;

    private final boolean isCorrect;


    public Answer(String value, boolean isCorrect) {
        this.value = value;
        this.isCorrect = isCorrect;
    }

    public boolean isCorrect() {
        return isCorrect;
    }


    public String getValue() {
        return value;
    }
}