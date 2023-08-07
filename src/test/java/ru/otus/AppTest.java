package ru.otus;

import org.junit.jupiter.api.Assertions;
import ru.otus.dao.ResourceProviderImpl;
import ru.otus.service.TestCreatorService;
import ru.otus.service.TestCreatorServiceImpl;

import java.io.IOException;

public class AppTest {

    @org.junit.jupiter.api.Test
    public void questionNumberTest() {
        TestCreatorService testCreatorService =
                new TestCreatorServiceImpl( new ResourceProviderImpl(
                        new ResourceProviderImpl.ResourceImpl("questAndAnsw.csv")));

        Assertions.assertEquals(5,(testCreatorService.createTest().size()));
    }
}
