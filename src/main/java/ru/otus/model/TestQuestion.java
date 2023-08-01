package ru.otus.model;

import java.util.List;

public class TestQuestion {
    private String question;

    private List<String> anserList;

    private String correctAnswer;

    public TestQuestion(List<String> anserList, String correctAnswer) {
        this.anserList = anserList;
        this.correctAnswer = correctAnswer;
    }

    public TestQuestion(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnserList() {
        return anserList;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnserList(List<String> anserList) {
        this.anserList = anserList;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
