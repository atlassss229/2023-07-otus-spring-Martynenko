package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import ru.otus.model.Question;
import ru.otus.model.Student;
import ru.otus.service.IOService;
import java.util.List;

@ExtendWith({OutputCaptureExtension.class})
@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class CvsStudentDaoImplTest {

    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private IOService ioService;

    @Test
    public void questionNumberTest() {
        List<Question> questionList = questionDao.questionList();
        Assertions.assertEquals(5, questionList.size());
    }

    @Test
    public void displayName(CapturedOutput capturedOutput) {

        Student student = new Student("StudentFirstName", "StudentLastName");
        ioService.printLine(student.getLastName());
        System.out.printf("expected student last name %s - in result %s",
                "StudentLastName", student.getLastName());

        Assertions.assertTrue(capturedOutput.getAll().contains("StudentLastName"));
    }
}
