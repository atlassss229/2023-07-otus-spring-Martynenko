package ru.otus;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.dao.StudentDaoImpl;
import ru.otus.service.IOService;
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
