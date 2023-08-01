package ru.otus.service;

import ru.otus.model.TestQuestion;

import java.io.IOException;
import java.util.Map;

public class RunServiceImpl implements RunService {

    private final TestCreatorServiceImpl testCreatorServiceImpl;

    public RunServiceImpl(TestCreatorServiceImpl testCreatorServiceImpl) {
        this.testCreatorServiceImpl = testCreatorServiceImpl;
    }

    @Override
    public void run() throws IOException {
        Map <String, TestQuestion> questionHashMap = testCreatorServiceImpl.createTest();
        for (String key : questionHashMap.keySet()) {
            System.out.println(questionHashMap.get(key).getQuestion());
            System.out.println(questionHashMap.get(key).getAnserList());
            System.out.println(questionHashMap.get(key).getCorrectAnswer());
        }
    }
}

