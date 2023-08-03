package ru.otus.model;

import java.util.List;

public class Question {
    private final Answer answer = new Answer();

    private final CorrectAnswer correctAnswer = new CorrectAnswer();

    private String question;

    public Question(List<String> anserList, String correctAnswer, String question) {
        this.answer.setAnswerList(anserList);
        this.correctAnswer.setCorrectAnswer(correctAnswer);
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswerList() {
        return answer.getAnswerList();
    }

    public String getCorrectAnswer() {
        return correctAnswer.getCorrectAnswer();
    }
}
