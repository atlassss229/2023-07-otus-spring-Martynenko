package ru.otus.service;

import ru.otus.model.Question;

import java.io.IOException;
import java.util.List;


public class RunServiceImpl implements RunService {

    private final TestCreatorService testCreatorService;

    public RunServiceImpl(TestCreatorServiceImpl testCreatorServiceImpl) {
        this.testCreatorService = testCreatorServiceImpl;
    }

    @Override
    public void run() throws IOException {
        List<Question> questionList = testCreatorService.createTest();
        for (Question question : questionList) {
            System.out.println(question.getQuestion());
            System.out.println(question.getAnswerList());
            System.out.println(question.getCorrectAnswer());
        }
    }
}

