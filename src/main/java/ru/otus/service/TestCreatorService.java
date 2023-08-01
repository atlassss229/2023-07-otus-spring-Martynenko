package ru.otus.service;

import ru.otus.model.TestQuestion;

import java.io.IOException;
import java.util.Map;

public interface TestCreatorService {
    Map<String, TestQuestion> createTest() throws IOException;
}
