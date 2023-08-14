package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import ru.otus.dao.CsvQuestionDao;
import ru.otus.dao.QuestionDao;
import ru.otus.model.Student;
import ru.otus.service.IOService;
import ru.otus.service.IOServiceImpl;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CvsStudentDaoImplTest {


    @org.junit.jupiter.api.Test
    public void questionNumberTest() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:questAndAnsw.csv");

        QuestionDao questionDao =
                new CsvQuestionDao(resource);

        Assertions.assertEquals(5, (questionDao.questionList().size()));
    }

    @org.junit.jupiter.api.Test
    public void displayName() {
        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));

        Student student = new Student("StudentFirstName", "StudentLastName");
        IOService ioService = new IOServiceImpl();
        ioService.printLine(student.getLastName());
        Assertions.assertEquals("StudentLastName", outputStreamCaptor.toString()
                .trim());
    }
}
