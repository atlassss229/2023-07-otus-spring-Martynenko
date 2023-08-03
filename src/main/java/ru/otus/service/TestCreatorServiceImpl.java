package ru.otus.service;

import ru.otus.dao.ResourceProviderImpl;
import ru.otus.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TestCreatorServiceImpl implements TestCreatorService {

    private final ResourceProviderImpl resourceProvider;

    public TestCreatorServiceImpl(ResourceProviderImpl resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    @Override
    public List<Question> createTest() throws IOException {
        List<Question> questionList = new ArrayList<>();
        try (InputStream stream = resourceProvider.getResource();
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            //вычитал заголовок, кстати, а зачем он?
            reader.readLine();
            while (reader.ready()) {
                String line = reader.readLine();
                List<String> lineList = List.of(line.split("/"));
                List<String> annswerList = List.of(lineList.get(1).split(","));
                Question question = new Question(
                        annswerList,
                        lineList.get(2),
                        lineList.get(0)
                );
                questionList.add(question);

            }
            return questionList;
        }
    }
}
