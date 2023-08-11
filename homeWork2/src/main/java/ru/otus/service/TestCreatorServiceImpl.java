package ru.otus.service;

import ru.otus.dao.ResourceProviderImpl;
import ru.otus.model.Answer;
import ru.otus.model.Question;

import java.util.ArrayList;
import java.util.List;

public class TestCreatorServiceImpl implements TestCreatorService {

    private final ResourceProviderImpl resourceProvider;

    public TestCreatorServiceImpl(ResourceProviderImpl resourceProvider) {
        this.resourceProvider = resourceProvider;
    }

    @Override
    public List<Question> createTest() {
        List<Question> questionList = new ArrayList<>();
        String file = resourceProvider.getFile();
        List<String> lineListNoHeader = removeHeader(file);
        for (String line : lineListNoHeader) {
            List<String> questionLineList = List.of(line.split(";"));
            Question question = getQuestion(questionLineList);
            questionList.add(question);
        }
        return questionList;
    }

    private static Question getQuestion(List<String> questionLineList) {
        String correctAnswer = questionLineList.get(2);
        List<String> answerStringList = List.of(questionLineList.get(1).split(","));
        List<Answer> answerList = new ArrayList<>();
        for (String answerString : answerStringList) {
            boolean isCorrectAnswer = answerString.equals(correctAnswer);
            Answer answer = new Answer(answerString, isCorrectAnswer);
            answerList.add(answer);
        }
        String questionString = questionLineList.get(0);
        return new Question(questionString, answerList);
    }

    private static List<String> removeHeader(String file) {
        List<String> lineList = List.of(file.split("\n"));
        List<String> lineListNoHeader = new ArrayList<>(lineList);
        lineListNoHeader.remove(0);
        return lineListNoHeader;
    }
}
