package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import ru.otus.config.AppProps;
import ru.otus.dao.CsvQuestionDao;
import ru.otus.dao.QuestionDao;
import ru.otus.model.Student;
import ru.otus.service.IOService;
import ru.otus.service.IOServiceImpl;

import java.io.*;
import java.util.Locale;

import static org.mockito.Mockito.mock;

public class CvsStudentDaoImplTest {

    @org.junit.jupiter.api.Test
    public void questionNumberTest() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:questAndAnsw_en.csv");

        AppProps appProps = new AppProps(resource, null, mock(Locale.class));

        QuestionDao questionDao =
                new CsvQuestionDao(appProps);

        int questionListSize = questionDao.questionList().size();

        System.out.printf("expected 5 questions - in result %s%n",
                questionListSize);
        Assertions.assertEquals(5, questionListSize);

    }

    @org.junit.jupiter.api.Test
    public void displayName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Student student = new Student("StudentFirstName", "StudentLastName");
        IOService ioService = new IOServiceImpl();
        ioService.printLine(student.getLastName());

        String compareValue = outputStreamCaptor.toString().trim();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.printf("expected student last name %s - in result %s",
                "StudentLastName", student.getLastName());

        Assertions.assertEquals("StudentLastName", compareValue);
    }
}
