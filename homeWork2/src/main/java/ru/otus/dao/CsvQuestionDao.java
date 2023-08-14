package ru.otus.dao;

import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;
import ru.otus.model.Answer;
import ru.otus.model.Question;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class CsvQuestionDao implements QuestionDao {

    private final Resource resource;

    public CsvQuestionDao(Resource resource) {
        this.resource = resource;
    }

    @Override
    public List<Question> questionList() {

        String file = getFileAsString();

        List<String> lineListNoHeader = removeHeader(file);
        List<Question> questionList = new ArrayList<>();
        for (String line : lineListNoHeader) {
            List<String> questionLineList = List.of(line.split(";"));
            Question question = getQuestion(questionLineList);
            questionList.add(question);
        }
        return questionList;
    }

    private String getFileAsString() {
        String file;
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            file = FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return file;
    }

    private Question getQuestion(List<String> questionLineList) {
        String correctAnswer = questionLineList.get(1);
        List<String> answerStringList = List.of(questionLineList.get(2).split(","));
        List<Answer> answerList = new ArrayList<>();
        for (String answerString : answerStringList) {
            boolean isCorrectAnswer = answerString.equals(correctAnswer);
            Answer answer = new Answer(answerString, isCorrectAnswer);
            answerList.add(answer);
        }
        String questionString = questionLineList.get(0);
        return new Question(questionString, answerList);
    }

    private List<String> removeHeader(String file) {
        List<String> lineList = List.of(file.split("\n"));
        List<String> lineListNoHeader = new ArrayList<>(lineList);
        lineListNoHeader.remove(0);
        return lineListNoHeader;
    }
}
