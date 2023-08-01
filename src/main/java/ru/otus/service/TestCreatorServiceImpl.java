package ru.otus.service;

import ru.otus.dao.FileAsResourseDaoImpl;
import ru.otus.model.TestQuestion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestCreatorServiceImpl implements TestCreatorService {

     private final FileAsResourseDaoImpl fileAsResourceDao;

    public TestCreatorServiceImpl(FileAsResourseDaoImpl fileAsResourceDao) {
        this.fileAsResourceDao = fileAsResourceDao;
    }

    @Override
    public Map<String, TestQuestion> createTest() throws IOException {
        InputStream stream = fileAsResourceDao.getDataFromFile();
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Map<String, TestQuestion> questionMap = new HashMap<>();
        while (reader.ready()) {
            String line = reader.readLine();
            addQuestions(line, questionMap);
            addAnswers(line, questionMap);
        }
        return questionMap;
    }

    private static void addAnswers(String line, Map<String, TestQuestion> questionMap) {
        if (line.contains("AN")) {
            List <String> lineList = List.of((line.split("/")));
            List <String> annswerList = List.of(lineList.get(1).split(","));
            String number = lineList.get(0).replace("AN", "");
            String coorectAnswer = lineList.get(2);
            if (questionMap.get(number) == null) {
                questionMap.put(number, new TestQuestion(annswerList, coorectAnswer));
            } else {
                questionMap.get(number).setAnserList(annswerList);
                questionMap.get(number).setCorrectAnswer(coorectAnswer);
            }
        }
    }

    private static void addQuestions(String line, Map<String, TestQuestion> questionMap) {
        if (line.contains("QS")) {
            List<String> lineList = List.of((line.split("/")));
            String question = lineList.get(1);
            String number = lineList.get(0).replace("QS", "");

            if (questionMap.get(number) == null) {
                questionMap.put(number, new TestQuestion(question));
            } else {
                questionMap.get(number).setQuestion(question);
            }
        }
    }
}
