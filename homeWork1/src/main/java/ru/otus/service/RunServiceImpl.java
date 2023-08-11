package ru.otus.service;

import ru.otus.model.Answer;
import ru.otus.model.Question;

import java.util.ArrayList;
import java.util.List;

public class RunServiceImpl implements RunService {

    private final TestCreatorService testCreatorService;

    public RunServiceImpl(TestCreatorServiceImpl testCreatorServiceImpl) {
        this.testCreatorService = testCreatorServiceImpl;
    }

    @Override
    public void run() {
        List<Question> questionList = testCreatorService.createTest();
        for (Question question : questionList) {
            System.out.println(question.getQuestion());
            List<String> answerStringList = new ArrayList<>();
            for (Answer answer : question.getAnswerList()) {
                answerStringList.add(answer.getValue());
            }
            System.out.println(answerStringList);
        }
    }
}

