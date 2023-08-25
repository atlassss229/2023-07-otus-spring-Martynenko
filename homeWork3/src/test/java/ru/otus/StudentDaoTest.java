package ru.otus;

import net.bytebuddy.utility.nullability.AlwaysNull;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.dao.StudentDaoImpl;
import ru.otus.model.Student;
import ru.otus.service.IOService;
import ru.otus.service.IOServiceImpl;
import ru.otus.service.MessageSourceService;

import static org.mockito.Mockito.*;

@SpringBootTest
public class StudentDaoTest {

    private IOService ioService;

    private MessageSourceService messageSourceService;

    @Test
    public void someTest() {
        ioService = mock(IOService.class);
        messageSourceService = mock(MessageSourceService.class);
        StudentDaoImpl studentDao = new StudentDaoImpl(ioService, messageSourceService);
        Assert.assertNotNull(studentDao.getStudent());
    }
}
