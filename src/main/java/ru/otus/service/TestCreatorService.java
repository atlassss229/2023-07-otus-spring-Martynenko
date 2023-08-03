package ru.otus.service;

import ru.otus.model.Question;

import java.io.IOException;
import java.util.List;

public interface TestCreatorService {
    List<Question> createTest() throws IOException;
}
