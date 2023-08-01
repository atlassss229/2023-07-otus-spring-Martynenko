package ru.otus.service;

import ru.otus.model.TestQuestion;

import java.io.IOException;
import java.util.Map;

public class RunService {

    private final TestCreatorService testCreatorService;

    public RunService(TestCreatorService testCreatorService) {
        this.testCreatorService = testCreatorService;
    }

    public void run() throws IOException {
        Map <String, TestQuestion> questionHashMap = testCreatorService.createTest();
        for (String key : questionHashMap.keySet()) {
            System.out.println(questionHashMap.get(key).getQuestion());
            System.out.println(questionHashMap.get(key).getAnserList());
            System.out.println(questionHashMap.get(key).getCorrectAnswer());
        }
    }
}

