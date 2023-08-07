package ru.otus.model;

import java.util.List;

public class Question {
    private final String question;

    private final List<Answer> answerList;


    public Question(String question, List<Answer> answerList) {
        this.answerList = answerList;
        this.question = question;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public String getQuestion() {
        return question;
    }
}
