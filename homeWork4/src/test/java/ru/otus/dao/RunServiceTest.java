package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.model.Answer;
import ru.otus.model.Question;
import ru.otus.model.Student;
import ru.otus.service.IOService;
import ru.otus.service.RunServiceImpl;

import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class RunServiceTest {

    @MockBean
    private CsvQuestionDao questionDao;

    @MockBean
    private IOService ioService;

    @MockBean
    private StudentDao studentDao;

    @Autowired
    private RunServiceImpl runService;

    @Test
    public void runServiceImplTest() {

        Student student = new Student("fName", "lNAme");
        when(studentDao.getStudent()).thenReturn(student);

        List<Question> questionList
                = List.of(
                new Question("question1", List.of(
                        new Answer("answer1", false),
                        new Answer("answer2", true))),
                new Question("question2", List.of(
                        new Answer("answer1", true),
                        new Answer("answer2", false))));

        when(questionDao.questionList()).thenReturn(questionList);
        when(ioService.readLine()).thenReturn("2");
        runService.run();
        int checkNumOfCooerctAnswers = 1;
        Assertions.assertEquals(student.getNumOfQuestionsAnswered(), checkNumOfCooerctAnswers);
    }
}
