package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import ru.otus.dao.ResourceProviderImpl;
import ru.otus.service.TestCreatorService;
import ru.otus.service.TestCreatorServiceImpl;

public class AppTest {



    @org.junit.jupiter.api.Test
    public void questionNumberTest() {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:questAndAnsw.csv");

        TestCreatorService testCreatorService =
                new TestCreatorServiceImpl( new ResourceProviderImpl(resource));

        Assertions.assertEquals(5,(testCreatorService.createTest().size()));
    }
}
