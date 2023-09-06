package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.model.Question;

import java.util.List;

import static org.mockito.Mockito.mock;
//@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"
})
public class CvsStudentDaoImplTest {

    @Autowired
    private QuestionDao questionDao;

    @Test
    public void questionNumberTest() {
//        ResourceLoader resourceLoader = new DefaultResourceLoader();
//        Resource resource = resourceLoader.getResource("classpath:questAndAnsw_en.csv");
//
//        AppProps appProps = new AppProps(resource, null, mock(Locale.class));

//        System.out.println(appProps);

        List<Question> questionList = questionDao.questionList();
        Assertions.assertNull(questionList);
//        QuestionDao questionDao =
//                new CsvQuestionDao(appProps);
//
//        int questionListSize = questionDao.questionList().size();
//
//        System.out.printf("expected 5 questions - in result %s%n",
//                questionListSize);
//        Assertions.assertEquals(5, questionListSize);

    }

//    @org.junit.jupiter.api.Test
//    public void displayName() {
//        ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
//        System.setOut(new PrintStream(outputStreamCaptor));
//
//        Student student = new Student("StudentFirstName", "StudentLastName");
//        IOService ioService = new IOServiceImpl();
//        ioService.printLine(student.getLastName());
//
//        String compareValue = outputStreamCaptor.toString().trim();
//        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
//        System.out.printf("expected student last name %s - in result %s",
//                "StudentLastName", student.getLastName());
//
//        Assertions.assertEquals("StudentLastName", compareValue);
//    }
}
