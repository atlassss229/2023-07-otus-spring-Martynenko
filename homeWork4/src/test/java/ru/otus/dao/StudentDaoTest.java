package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.model.Student;
import ru.otus.service.IOService;
import ru.otus.service.MessageSourceService;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class StudentDaoTest {

    @MockBean
    private IOService ioService;

    @Autowired
    private StudentDaoImpl studentDao;

    @Test
    public void testStudentDao() {
        String testName = "studentName";
        when(ioService.readLine()).thenReturn(testName);
        Student student = studentDao.getStudent();
        Assertions.assertEquals(student.getFirstName(), testName);
    }


}
